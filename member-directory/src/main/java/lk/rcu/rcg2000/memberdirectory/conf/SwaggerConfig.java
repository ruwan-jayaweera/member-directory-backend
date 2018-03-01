package lk.rcu.rcg2000.memberdirectory.conf;

import com.querydsl.core.types.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.RequestMethod;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String API_ERROR = "ApiError";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).groupName("member-directory")
                .useDefaultResponseMessages(false)
                .ignoredParameterTypes(Authentication.class, Predicate.class)
                .globalResponseMessage(RequestMethod.GET,
                        newArrayList(new ResponseMessageBuilder().code(HttpStatus.OK.value())
                                .message(HttpStatus.OK.getReasonPhrase())
                                .build()))
                .globalResponseMessage(RequestMethod.POST,
                        newArrayList(new ResponseMessageBuilder().code(HttpStatus.CREATED.value())
                                        .message(HttpStatus.CREATED.getReasonPhrase())
                                        .build(),
                                new ResponseMessageBuilder().code(HttpStatus.BAD_REQUEST.value())
                                        .message(HttpStatus.BAD_REQUEST.getReasonPhrase()
                                                .concat(" (Validation fails)"))
                                        .responseModel(new ModelRef(API_ERROR))
                                        .build(),
                                new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                                        .message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
                                        .responseModel(new ModelRef(API_ERROR))
                                        .build(),
                                new ResponseMessageBuilder().code(HttpStatus.UNAUTHORIZED.value())
                                        .message(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                                        .responseModel(new ModelRef(API_ERROR))
                                        .build(),
                                new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
                                        .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                                        .responseModel(new ModelRef(API_ERROR))
                                        .build()))
                .globalResponseMessage(RequestMethod.PUT,
                        newArrayList(new ResponseMessageBuilder().code(HttpStatus.NO_CONTENT.value())
                                        .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                                        .build(),
                                new ResponseMessageBuilder().code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
                                        .message(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase())
                                        .responseModel(new ModelRef(API_ERROR))
                                        .build(),
                                new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
                                        .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                                        .responseModel(new ModelRef(API_ERROR))
                                        .build()))
                .globalResponseMessage(RequestMethod.DELETE,
                        newArrayList(new ResponseMessageBuilder().code(HttpStatus.NO_CONTENT.value())
                                        .message(HttpStatus.NO_CONTENT.getReasonPhrase())
                                        .build(),
                                new ResponseMessageBuilder().code(HttpStatus.NOT_FOUND.value())
                                        .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                                        .responseModel(new ModelRef(API_ERROR))
                                        .build()))
                .directModelSubstitute(Date.class, Long.class)
                .produces(new HashSet<>(Collections.singletonList("application/json")))
                .consumes(new HashSet<>(Collections.singletonList("application/json")))
                .select()
                .apis(RequestHandlerSelectors.basePackage("lk.rcu.rcg2000.memberdirectory"))
                .build();
    }
}
