package com.codingchallenge.api_nearby_shops.security.configuration;

import com.codingchallenge.api_nearby_shops.service.UserDetailsServiceImpl;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    /*
        Extract the token from the request header and check the validity of this token
     and set the current user as an authenticated user.
     */
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        final String requestAuthorisation = httpServletRequest.getHeader("Authorization");
        String username = null;
        String jwtToken = null;

            if(requestAuthorisation != null && requestAuthorisation.startsWith("auth-token ")){

                jwtToken = requestAuthorisation.substring(11);

                try{
                    username = this.jwtTokenUtil.extractUsernameFromToken(jwtToken);
                }catch (IllegalArgumentException e){
                    System.out.println("Unable to get JWT Token");
                }catch (ExpiredJwtException e){
                    System.out.println("JWT Token has expired");
                }
            }else{
                logger.warn("JWT Token does not start with the right expression or the request authorisation was not set");
            }

            if(username != null && SecurityContextHolder.getContext().getAuthentication() == null){

                UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

                if(this.jwtTokenUtil.isTokenValid(jwtToken, userDetails)){

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                }
            }
        filterChain.doFilter(httpServletRequest, httpServletResponse);

    }
}
