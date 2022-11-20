package pe.upc.leasingya.security.jwt;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;
import pe.upc.leasingya.security.service.UserDetailServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtTokenFilter extends OncePerRequestFilter {

    private final static Logger logger = LoggerFactory.getLogger(JwtTokenFilter.class);

    @Autowired
    JwtProvider jwtProvider;

    @Autowired
    UserDetailServiceImpl userDetailService;

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwt = parseJwt(request);

            if (jwt != null && jwtProvider.validateJwtToken(jwt)) {
                String usernameUsuario = jwtProvider.getUsernameFromToken(jwt);

                UserDetails userDetails = userDetailService.loadUserByUsername(usernameUsuario);

                UsernamePasswordAuthenticationToken authenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        } catch (Exception e) {
            logger.error("No se puede enviar la Autenticación del Usuario: ", e);
        }

        filterChain.doFilter(request, response);
    }

    private String parseJwt(HttpServletRequest request) {

        String header = request.getHeader("Authorization");

        if (StringUtils.hasText(header) && header.startsWith("Bearer ")) {

            return header.substring(7);
        }
        return null;
    }
}
