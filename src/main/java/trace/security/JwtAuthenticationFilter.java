package trace.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import trace.service.interfaces.JwtService;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final CustomUserDetailsService customUserDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        if(authHeader==null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response); // because we check authentication not authorization and not every field will be authenticated by default, example register or login page
            return;
        }

        String jwt = authHeader.replace("Bearer ", "");
        //Alternatively you can also write :
        // String jwt = authHeader.substring(7);

        boolean validToken = jwtService.validateToken(jwt);
        if(!validToken){
            filterChain.doFilter(request,response);
            return;
        }

        String email = jwtService.extractEmail(jwt);
        UserDetails user = customUserDetailsService.loadUserByUsername(email);

        if (SecurityContextHolder.getContext().getAuthentication() == null) { // what if it already has some other authentication method ( lets say oauth)
            Authentication authentication = new UsernamePasswordAuthenticationToken(user,
                    null,
                    user.getAuthorities());

            SecurityContextHolder // context's holder
                    .getContext() // actual context
                    .setAuthentication(authentication); // authentication object put in backpack
        }

        filterChain.doFilter(request, response); // done authenticating using jwt and security context holder is set, now move forward
    }
}
