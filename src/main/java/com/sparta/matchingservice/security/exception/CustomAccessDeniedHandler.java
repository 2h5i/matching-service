package com.sparta.matchingservice.security.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.matchingservice.security.dto.SecurityExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
@Slf4j
public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setContentType("application/json");
        try {
            String json = new ObjectMapper().writeValueAsString(new SecurityExceptionDto(response.getStatus(), "FORBIDDEN"));
            response.getWriter().write(json);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new AccessDeniedException("실험1");
        }
    }
}
