package isa.apoteka.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import isa.apoteka.domain.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import isa.apoteka.security.TokenUtils;


//Filter koji ce presretati svaki zahtev klijenta ka serveru
//Sem nad putanjama navedenim u WebSecurityConfig.configure(WebSecurity web)
public class TokenAuthenticationFilter extends OncePerRequestFilter {

    private TokenUtils tokenUtils;

    private UserDetailsService userDetailsService;

    public TokenAuthenticationFilter(TokenUtils tokenHelper, UserDetailsService userDetailsService) {
        this.tokenUtils = tokenHelper;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        String authToken = tokenUtils.getToken(request);

        if (authToken != null) {
            String email = tokenUtils.getEmailFromToken(authToken);

            if (email != null) {
                UserDetails userDetails = userDetailsService.loadUserByUsername(email);
                User user = (User) userDetails;

                if (tokenUtils.validateToken(authToken, userDetails) && (user.getLastPasswordResetDate() != null || isAllowedRoute(request))) {
                    TokenBasedAuthentication authentication = new TokenBasedAuthentication(userDetails);
                    authentication.setToken(authToken);
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }

        chain.doFilter(request, response);
    }

    private Boolean isAllowedRoute(HttpServletRequest request) {
        return request.getServletPath().equals("/auth/change-password") || request.getServletPath().equals("/auth/getRole");
    }

}