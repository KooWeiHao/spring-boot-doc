package eric.koo.spring.boot.doc.core;

import eric.koo.spring.boot.doc.api.entity.DocumentBean;
import eric.koo.spring.boot.doc.core.dao.DocumentDao;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackageClasses = {DocumentBean.class})
@EnableJpaRepositories(basePackageClasses = {DocumentDao.class})
public class DocApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(DocApplication.class);
    }
}
