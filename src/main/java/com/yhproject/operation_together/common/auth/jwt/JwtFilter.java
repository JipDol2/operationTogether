package com.yhproject.operation_together.common.auth.jwt;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;
import lombok.RequiredArgsConstructor;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.MalformedInputException;

@RequiredArgsConstructor
@WebFilter(urlPatterns = "/api/auth/*")
public class JwtFilter extends OncePerRequestFilter {

    private final JwtTokenProvider jwtTokenProvider;

    /**
     * Authorization 이 필요할때 js 에서 api 호출로 doFilterInternal 함수를 호출하여 확인하면 된다.(ex, result.js 참고)
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String jwt = resolveToken(request);
        try{
            if (StringUtils.hasText(jwt) && jwtTokenProvider.validateToken(jwt)) {
                Long operationId = jwtTokenProvider.getOperationId(jwt);
                request.setAttribute("operationId", operationId);
                filterChain.doFilter(request, response);
                return;
            }
        }catch(MalformedInputException e){
            response.sendError(401,"손상된 토큰입니다.");
        }catch (ExpiredJwtException e){
            response.sendError(401,"만료된 토큰입니다.");
        }catch (UnsupportedJwtException e){
            response.sendError(401,"지원하지 않는 토큰입니다.");
        }catch (SignatureException e){
            response.sendError(401,"시그니처 검증에 실패한 토큰입니다.");
        }
    }

    private String resolveToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
