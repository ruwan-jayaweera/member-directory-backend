package lk.rcu.rcg2000.memberdirectory.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * This class overrides default spring web behaviour for Member Directory.
 */
@Configuration
public class MemDirWebMvcConfigurerAdapter extends WebMvcConfigurerAdapter {

    /**
     * Enabling CORS for the whole application. By default deletes are not allowed, so need to specify all allowed methods
     * @param registry
     */
    @Override
    public void addCorsMappings(final CorsRegistry registry) {

        registry.addMapping("/**").allowedMethods("PUT","DELETE","POST","GET","OPTIONS");
    }

}
