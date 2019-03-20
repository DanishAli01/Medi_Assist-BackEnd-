package medi_assistbe.mongobd;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JwtConfig {

    @Autowired
    private JWTFilter jwtFilter;

    @Bean
    public FilterRegistrationBean<JWTFilter> filterFilterRegistrationBean(){
        FilterRegistrationBean<JWTFilter> filterFilterRegistrationBean = new FilterRegistrationBean<>();
        filterFilterRegistrationBean.setFilter(jwtFilter);
        filterFilterRegistrationBean.addUrlPatterns("/secured/*");
        return filterFilterRegistrationBean;

    }

}

