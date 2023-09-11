package com.serviceA.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.serviceA.exception.ErrorResponse;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class AuthFilter extends OncePerRequestFilter {

	@Autowired
	private Authservice authservice;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String authToken = null;
		String userToken = request.getHeader("Authorization");
		if (userToken != null && userToken.startsWith("Bearer ")) {

			authToken = userToken.substring(7);
			try {
				authservice.validateAuth(authToken);
				filterChain.doFilter(request, response);
			} catch (Exception e) {
				//throw new AuthTokenInvalidException(authToken);
				logger.info("Token is not valid");
                ErrorResponse errorResponse = new ErrorResponse("Invalid Token","Token Not Recognised",HttpStatus.BAD_REQUEST);
			    response.setStatus(HttpStatus.BAD_REQUEST.value());
	            response.getWriter().write(convertObjectToJson(errorResponse));
			}
		}
	}
	
	public String convertObjectToJson(ErrorResponse object) throws JsonProcessingException {
        if (object == null) {
            return null;
        }
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }
	
	private List<String> excludeUrlPatterns = new ArrayList<String>(Arrays.asList("/swagger-ui.html",
			"/swagger-uui.html", "/webjars/springfox-swagger-ui/springfox.css",
			"/webjars/springfox-swagger-ui/swagger-ui-bundle.js", "/webjars/springfox-swagger-ui/swagger-ui.css",
			"/webjars/springfox-swagger-ui/swagger-ui-standalone-preset.js",
			"/webjars/springfox-swagger-ui/springfox.js", "/swagger-resources/configuration/ui",
			"/webjars/springfox-swagger-ui/favicon-32x32.png", "/swagger-resources/configuration/security",
			"/swagger-resources", "/v2/api-docs",
			"/webjars/springfox-swagger-ui/fonts/titillium-web-v6-latin-700.woff2",
			"/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-regular.woff2",
			"/webjars/springfox-swagger-ui/fonts/open-sans-v15-latin-700.woff2",
			"/webjars/springfox-swagger-ui/favicon-16x16.png", // ));
			"/swagger-ui/", "/swagger-ui/index.html", "/favicon.ico", "/swagger-ui/favicon-32x32.png",
			"/swagger-ui/favicon-16x16.png", "/v3/api-docs", "/swagger-ui/springfox.css",
			"/swagger-ui/swagger-ui-bundle.js", "/swagger-ui/swagger-ui-standalone-preset.js",
			"/swagger-ui/springfox.js", "/swagger-ui/swagger-ui.css", "/swagger-ui/swagger-initializer.js",
			"/v3/api-docs/swagger-config"));

 

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getRequestURI();
        if (excludeUrlPatterns.contains(path)) {
            return true;
        } else {
            return false;
        }
    }

 

}
