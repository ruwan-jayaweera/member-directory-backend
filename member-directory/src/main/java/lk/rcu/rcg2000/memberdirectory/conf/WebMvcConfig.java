package lk.rcu.rcg2000.memberdirectory.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.Formatter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addFormatters (FormatterRegistry registry) {
        EpochDateFormatter epochDateFormatter = new EpochDateFormatter();
        registry.addFormatter(epochDateFormatter);
    }

    /**
     * Formats an epoch/unix string to a Date
     *
     * We're using this to bring Spring's URL date-handling into line with Jackson's date-handling.
     */
    private class EpochDateFormatter implements Formatter<Date> {
        @Override
        public Date parse(String s, Locale locale) throws ParseException {
            return new Date(Long.parseLong(s));
        }

        @Override
        public String print(Date date, Locale locale) {
            return Long.toString(date.getTime());
        }
    }
}