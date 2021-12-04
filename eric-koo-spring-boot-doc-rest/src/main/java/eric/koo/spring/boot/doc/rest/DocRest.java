package eric.koo.spring.boot.doc.rest;

import eric.koo.spring.boot.doc.core.DocApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Arrays;

public class DocRest {
	private static final Logger logger = LoggerFactory.getLogger(DocRest.class);

	public static void main(String[] args) {
		ConfigurableApplicationContext cac =SpringApplication.run(DocApplication.class, args);
		if(logger.isDebugEnabled()){
			String[] beanNames = cac.getBeanDefinitionNames();
			Arrays.sort(beanNames);
			for(String beanName: beanNames){
				logger.debug(beanName);
			}
		}
	}

}
