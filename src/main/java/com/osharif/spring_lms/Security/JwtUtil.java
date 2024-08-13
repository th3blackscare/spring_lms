package com.osharif.spring_lms.Security;

import com.osharif.spring_lms.Security.Models.Token;
import com.osharif.spring_lms.Security.Models.User;
import com.osharif.spring_lms.Security.Repo.UserRepository;
import com.osharif.spring_lms.Security.Service.TokenService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.time.Instant;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;

@Component
public class JwtUtil {

    @Value("${jwt.private.key}")
    private String privateKeyPath;

    @Value("${jwt.public.key}")
    private String publicKeyPath;

    @Value("${jwt.expiration}")
    private long expiration = 3600000;

    private KeyPair keyPair;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private TokenService tokenService;

    @PostConstruct
    public void init() throws Exception {
        File privateKeyFile = new File(privateKeyPath);
        File publicKeyFile = new File(publicKeyPath);

        // if the key paris doesn't exist, will create and save them
        if (!privateKeyFile.exists() || !publicKeyFile.exists()) {
            generateAndSaveKeyPair(privateKeyFile, publicKeyFile);
        }

        byte[] privateKeyBytes = Files.readAllBytes(privateKeyFile.toPath());
        byte[] publicKeyBytes = Files.readAllBytes(publicKeyFile.toPath());

        PKCS8EncodedKeySpec privateKeySpec = new PKCS8EncodedKeySpec(privateKeyBytes);
        X509EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(publicKeyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        PrivateKey privateKey = keyFactory.generatePrivate(privateKeySpec);
        PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

        keyPair = new KeyPair(publicKey, privateKey);
    }

    private void generateAndSaveKeyPair(File privateKeyFile, File publicKeyFile) throws Exception {
        System.out.println("Generating Key");
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        KeyPair keyPair = keyPairGenerator.generateKeyPair();

        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();

        try (FileOutputStream fos = new FileOutputStream(privateKeyFile)) {
            fos.write(privateKey.getEncoded());
        }

        try (FileOutputStream fos = new FileOutputStream(publicKeyFile)) {
            fos.write(publicKey.getEncoded());
        }
    }

    @Bean
    public PublicKey publicKey() {
        return keyPair.getPublic();
    }

    @Bean
    public PrivateKey privateKey() {
        return keyPair.getPrivate();
    }

    public String extractUsername(String token) {
        System.out.println(extractAllClaims(token));
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(keyPair.getPublic())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails username, String clientId) {
        Optional<User> user = userRepository.findByUsername(username.getUsername());
        return createToken(user.get().getId(), user.get().getUsername(), clientId);
    }

    private String createToken(Long id, String username, String clientId) {
        Token token = saveTokenToDB(id, username, clientId);
        Map<String, Object> claims = new HashMap<>();
        claims.put(Claims.SUBJECT, token.getUsername());
        claims.put(Claims.ID, token.getId());
        claims.put(Claims.ISSUER, token.getClient_id());

        return Jwts.builder()
                .setHeaderParam("alg", "RS256")
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setIssuedAt(new Date(token.getCreated_at().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
                .setExpiration(new Date(token.getExpires_at().atZone(ZoneId.systemDefault()).toInstant().toEpochMilli()))
                .signWith(keyPair.getPrivate(), SignatureAlgorithm.RS256)
                .compact();
    }

    private Token saveTokenToDB(Long id, String username, String clientId) {
        Token token = new Token();
        token.setUser_id(id);
        token.setUsername(username);
        token.setClient_id(clientId);
        token.setCreated_at(Instant.ofEpochMilli(System.currentTimeMillis()).atZone(ZoneId.systemDefault()).toLocalDateTime());
        token.setExpires_at(Instant.ofEpochMilli(System.currentTimeMillis() + (expiration*1000)).atZone(ZoneId.systemDefault()).toLocalDateTime());
        return tokenService.saveToken(token);
    }

    public Boolean validateToken(String token, String username) {
        final String extractedUsername = extractUsername(token);
        return (extractedUsername.equals(username) && !isTokenExpired(token));
    }
}
