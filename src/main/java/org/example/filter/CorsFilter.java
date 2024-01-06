package org.example.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.utils.Const;
import org.junit.jupiter.api.Order;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Order(Const.ORDER_CORS)
public class CorsFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        this.addCorsHeader(request, response);
        if ("OPTIONS".equals(request.getMethod())) {
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }
        chain.doFilter(request, response);
    }

    private void addCorsHeader(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Headers", "access-control-allow-origin, authority, content-type, version-info, X-Requested-With, Authorization");
        response.addHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, PATCH, OPTIONS");
        response.addHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Credentials", "true");//这行是关键



//        response.addHeader("Access-Control-Allow-Credentials", request.getHeader("Origin"));
//
//        response.addHeader("Access-Control-Allow-Origin", "http://loaclhost:5173");
//        response.addHeader("Access-Control-Allow-Headers", "Authorization, Content-Type");
//        response.setHeader("Access-Control-Allow-Origin", "http://localhost:5173");//不可以传*
//        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, HEAD,PUT");
//        response.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

    }
}
