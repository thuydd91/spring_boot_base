package global.techhub.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static java.util.Arrays.asList;
import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
@Profile("!production")
public class SwaggerConfig {

    @Value("${app.url}")
    private String resourceUrl;

    @Value("${app.clientId}")
    private String clientId;

    @Value("${app.clientSecret}")
    private String clientSecret;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(asList(securityScheme()))
                .securityContexts(asList(securityContext()));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
                .clientId(clientId)
                .clientSecret(clientSecret)
                .scopeSeparator(" ")
                .useBasicAuthenticationWithAccessCodeGrant(true)
                .build();
    }

    private SecurityScheme securityScheme() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
                .tokenEndpoint(new TokenEndpoint(resourceUrl + "/oauth/token", "code"))
                .tokenRequestEndpoint(
                        new TokenRequestEndpoint(resourceUrl + "/oauth/authorize", clientId, clientSecret))
                .build();

        SecurityScheme oauth = new OAuthBuilder().name("app_oauth")
                .grantTypes(asList(grantType))
                .scopes(asList(scopes()))
                .build();
        return oauth;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(asList(new SecurityReference("app_oauth", scopes())))
                .forPaths(regex("/api.*"))
                .build();
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = {
                new AuthorizationScope("read", "for read operations"),
                new AuthorizationScope("write", "for write operations")};
        return scopes;
    }
}
