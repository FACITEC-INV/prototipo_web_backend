package py.edu.facitec.pinv01_267.pinv01_267_ws.config;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
@Profile("dev")
public class RequestLoggingFilter extends OncePerRequestFilter {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(request);

        filterChain.doFilter(wrappedRequest, response);

        Map<String, Object> logMap = new LinkedHashMap<>();
        logMap.put("method", request.getMethod());
        logMap.put("uri", request.getRequestURI());
        logMap.put("params", request.getQueryString());
        
        String rawBody = wrappedRequest.getContentAsString();
        try {
            JsonNode jsonBody = objectMapper.readTree(rawBody);
            logMap.put("body", jsonBody);
        } catch (Exception e) {
            logMap.put("body", rawBody);
        }

        try {
            String jsonLog = objectMapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(logMap);
            logger.info(jsonLog);
        } catch (Exception e) {
            logger.warn("Failed to log request as JSON", e);
        }
    }
}
