package eric.koo.spring.boot.doc.core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/");
        registry.addViewController("/{spring:\\w+}").setViewName("forward:/");
//        registry.addViewController("/**/{spring:\\w+}").setViewName("forward:/");
//        registry.addViewController("/{spring:\\w+}")
//                .setViewName("forward:/");
//        registry.addViewController("/**/{spring:\\w+}")
//                .setViewName("forward:/");
//        registry.addViewController("/{spring:\\w+}/**{spring:?!(\\.js|\\.css)$}")
//                .setViewName("forward:/");
//        registry.addViewController("document/**").setViewName("forward:/");
    }
}
