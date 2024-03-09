package global.techhub.base.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.channel.ChannelProcessingFilter;
import global.techhub.base.filter.CORSFilter;
import global.techhub.base.service.user.BaseUserDetailsService;

import java.util.Optional;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CORSFilter corsFilter;

    private final BaseUserDetailsService baseUserDetailsService;

    @Override
    public void configure(HttpSecurity http) throws Exception {

        Optional.ofNullable(corsFilter).ifPresent(filter -> http.addFilterBefore(filter, ChannelProcessingFilter.class));

        http
                .cors().and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                .antMatchers("/", "/js/**", "/css/**", "/images/**", "/webjars/**").permitAll()
                .antMatchers("/h2-console/**", "/api/users/register/**").permitAll()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/swagger-ui.html/**").authenticated()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .permitAll()
                .and()
                .logout()
                .permitAll();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**")
                .antMatchers(HttpMethod.GET, "/login/**", "/register/**", "/active/**", "/logout/**", "/", "/api-docs/**")
                .antMatchers(HttpMethod.OPTIONS, "/**");
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(baseUserDetailsService);
    }
}
