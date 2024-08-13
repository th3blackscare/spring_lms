package com.osharif.spring_lms.Security.filters;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import com.osharif.spring_lms.Security.Service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ServiceAuthenticationFilter extends AbstractPreAuthenticatedProcessingFilter {
    @Lazy
    @Autowired
    public ServiceAuthenticationFilter(AuthenticationManager authenticationManager) {
        setAuthenticationManager(authenticationManager);
    }    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Override
    protected Object getPreAuthenticatedPrincipal(HttpServletRequest request) {
        return request.getHeader("X-SERVICE-TO-SERVICE");
    }

    @Override
    protected Object getPreAuthenticatedCredentials(HttpServletRequest request) {
        return "N/A";
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        // Only authenticate if the service-to-service header is present
        String serviceHeader = ((HttpServletRequest) request).getHeader("X-SERVICE-TO-SERVICE");
        if (serviceHeader != null) {
            System.out.println("success");
            UserDetails userDetails = this.userDetailsService.loadUserByUsername("osharif");
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails((HttpServletRequest) request));
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            super.doFilter(request, response, chain);
        } else {
            System.out.println("fail");
            chain.doFilter(request, response);
        }
    }
}
