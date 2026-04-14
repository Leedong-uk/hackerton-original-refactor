package backend.kb_hack.global.security.filter;

import backend.kb_hack.global.common.exception.enums.BadStatusCode;
import backend.kb_hack.global.common.exception.type.BadRequestException;
import backend.kb_hack.global.common.exception.type.ServerErrorException;
import backend.kb_hack.global.common.exception.type.UnAuthorizedException;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class AuthenticationErrorFilter extends OncePerRequestFilter {


    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {

        try {
            filterChain.doFilter(request, response);

        } catch (ExpiredJwtException e) {
            throw new UnAuthorizedException(BadStatusCode.TOKEN_EXPIRED_EXCEPTION);

        } catch (io.jsonwebtoken.security.SignatureException | SecurityException e) {
            throw new BadRequestException(BadStatusCode.INVALID_TOKEN_SIGNATURE);

        } catch (UnsupportedJwtException | MalformedJwtException | IllegalArgumentException e) {
            throw new UnAuthorizedException(BadStatusCode.TOKEN_EXPIRED_EXCEPTION);

        } catch (ServletException e) {
            throw new ServerErrorException(BadStatusCode.INTERNAL_SERVER_EXCEPTION);
        }
    }

    private String extractClientIp(HttpServletRequest request) {
        String clientIp = request.getHeader("X-Forwarded-For");
        if (clientIp != null && !clientIp.isBlank()) {
            return clientIp.split(",")[0].trim();
        }
        return request.getRemoteAddr();
    }
}

