package pe.upc.pawfectcaremicroservices.iam_service.infrastructure.authorization.sfs.pipeline;

import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.authorization.sfs.model.UsernamePasswordAuthenticationTokenBuilder;
import pe.upc.pawfectcaremicroservices.iam_service.infrastructure.tokens.jwt.BearerTokenService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.lang.NonNull;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.util.AntPathMatcher;

import java.io.IOException;
import java.util.List;

/**
 * Bearer Authorization Request Filter.
 * <p>
 * This class is responsible for filtering requests and setting the user authentication.
 * It extends the OncePerRequestFilter class.
 * </p>
 * @see OncePerRequestFilter
 */
public class BearerAuthorizationRequestFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(BearerAuthorizationRequestFilter.class);
    private final BearerTokenService tokenService;

    @Qualifier("defaultUserDetailsService")
    private final UserDetailsService userDetailsService;

    // Rutas públicas que no requieren autenticación (con patrones)
    private static final List<String> PUBLIC_PATHS = List.of(
            "api/v1/authentication/sign-in",
            "api/v1/authentication/sign-up",
            "/api/v1/authentication/**",
            "/api/v1/auth/google/sign-in",
            "/api/v1/auth/google/sign-up",
            "/v3/api-docs/**",
            "/swagger-ui.html",
            "/swagger-ui/**",
            "/swagger-resources/**",
            "/webjars/**",

            // Actuator endpoints (IMPORTANTES para health checks)
            "/actuator/**",
            "/actuator/health",
            "/actuator/info",

            // Eureka endpoints (si es necesario)
            "/eureka/**"
    );

    private static final AntPathMatcher pathMatcher = new AntPathMatcher();

    public BearerAuthorizationRequestFilter(BearerTokenService tokenService, UserDetailsService userDetailsService) {
        this.tokenService = tokenService;
        this.userDetailsService = userDetailsService;
    }

    /**
     * This method is responsible for filtering requests and setting the user authentication.
     * @param request The request object.
     * @param response The response object.
     * @param filterChain The filter chain object.
     */
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        String path = request.getRequestURI();

        LOGGER.info("Request path: {}", path); // <-- Agrega este log
        LOGGER.info("Request URI: {}", path);
        LOGGER.info("Servlet path: {}", request.getServletPath());
        //boolean isPublic = PUBLIC_PATHS.stream().anyMatch(pattern -> pathMatcher.match(pattern, path));
        boolean isPublic = false;
        for (String pattern : PUBLIC_PATHS) {
            boolean matchesUri = pathMatcher.match(pattern, path);
            boolean matchesServlet = pathMatcher.match(pattern, request.getServletPath());
            LOGGER.info("Comparing path '{}' with pattern '{}': URI match={}, ServletPath match={}", path, pattern, matchesUri, matchesServlet);
            if (matchesUri || matchesServlet) {
                isPublic = true;
                LOGGER.info("Matched public path: {}", pattern);
                break;
            }
        }

        LOGGER.info("Is public: {}", isPublic); // <-- Y este
        if (isPublic) {
            LOGGER.info("✅ Allowing public access to: {}", path);
            filterChain.doFilter(request, response);
            return;
        }
        // ... resto igual
        try {
            String token = tokenService.getBearerTokenFrom(request);
            LOGGER.info("Token: {}", token);
            if (token != null && tokenService.validateToken(token)) {
                String username = tokenService.getUsernameFromToken(token);
                var userDetails = userDetailsService.loadUserByUsername(username);
                SecurityContextHolder.getContext().setAuthentication(UsernamePasswordAuthenticationTokenBuilder.build(userDetails, request));
            } else {
                LOGGER.info("Token is not valid");
            }
        } catch (Exception e) {
            LOGGER.error("Cannot set user authentication: {}", e.getMessage());
        }
        filterChain.doFilter(request, response);
    }
}