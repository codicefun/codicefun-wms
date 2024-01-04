package com.codicefun.wms.interceptor;

import com.codicefun.wms.entity.vo.ResponseVO;
import com.codicefun.wms.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    public LoginInterceptor(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws
            Exception {
        String token = request.getHeader("token");
        boolean isValidated = jwtUtil.validateToken(token);

        if (!isValidated) {
            ResponseVO<Object> responseVO = ResponseVO.fail();
            ObjectMapper objectMapper = new ObjectMapper();
            String json = objectMapper.writeValueAsString(responseVO);
            response.getWriter().print(json);
            return false;
        }

        return true;
    }

}
