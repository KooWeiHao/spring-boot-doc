package eric.koo.spring.boot.doc.core;

import eric.koo.spring.boot.doc.api.entity.DocumentBean;
import eric.koo.spring.boot.doc.core.dao.DocumentDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EntityScan(basePackageClasses = {DocumentBean.class})
@EnableJpaRepositories(basePackageClasses = {DocumentDao.class})
public class DocApplication extends SpringBootServletInitializer {
    private static final Logger logger = LoggerFactory.getLogger(DocApplication.class);

    @Value("${spring.boot.doc.frontend.allowed.origin}")
    private List<String> allowedOrigin;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DocApplication.class);
    }

    @Bean
    public FilterRegistrationBean<CorsFilter> simpleCorsFilter() {
        // *** URL below needs to match the client URL and port ***
        logger.info("Whitelisted origins - {}", allowedOrigin);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.setAllowedOrigins(allowedOrigin);
        config.setAllowedMethods(Arrays.asList("POST","PUT","GET","OPTIONS","DELETE"));
        config.setAllowedHeaders(Arrays.asList("X-Requested-With", "accept", "authorization","content-type"));
        source.registerCorsConfiguration("/**", config);
        FilterRegistrationBean<CorsFilter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return bean;
    }
}
