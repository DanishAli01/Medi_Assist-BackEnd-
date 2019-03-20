package medi_assistbe.mongobd;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Component
public class JWTFilter extends GenericFilterBean {
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        final HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String token = request.getHeader("Token");

        if(token == null || !token.startsWith("Bearer-")){
            throw new ServletException("403 - Token Not Found");
        }

        try {

            final Claims claims = Jwts.parser().setSigningKey("123#&zcvAWEE999").parseClaimsJws(token.substring(7)).getBody();
            System.out.println("Token Key "+claims.getSubject());
            request.setAttribute("claims",claims);
        }
        catch (final SignatureException e){

            throw new ServletException("402 - Token Not Found");
        }

        filterChain.doFilter(servletRequest,servletResponse);
    }
}
