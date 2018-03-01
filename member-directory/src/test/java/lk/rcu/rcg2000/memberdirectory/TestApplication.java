package lk.rcu.rcg2000.memberdirectory;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@SpringBootApplication
public class TestApplication {

    @Configuration
    @EnableWebSecurity
    @EnableGlobalMethodSecurity(jsr250Enabled = true, proxyTargetClass = true)
    @Import(EnableSpringDataWebSupport.QuerydslActivator.class)
    public static class WebMvcTestConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.csrf().disable();
        }
    }
}
