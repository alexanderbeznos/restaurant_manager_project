package project.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import project.security.AuthProviderImpl;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"project"})
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private AuthProviderImpl authProvider;

    @Autowired
    public WebSecurityConfig(@Lazy AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
//                .antMatchers("/auth/admin/** ").hasRole("ADMIN")
//                .antMatchers("/auth/admin/** ").hasAnyRole("ADMIN")
//                .antMatchers("/auth/** ").hasAnyRole("ADMIN", "USER")

//                .antMatchers("/registration", "/login").anonymous()
//                .antMatchers("/main").authenticated()
                .antMatchers("/registration", "/login").anonymous()
                .antMatchers("/menu/**").permitAll()
                .antMatchers("/main/**", "/restaurant/**").authenticated()
//                .antMatchers("/main").permitAll()

                .and().csrf().disable()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/login/process")
                .usernameParameter("login")
                .failureUrl("/login?error=true")
                .and()
                .exceptionHandling()
//                .accessDeniedPage("/list")
                .and().logout().logoutSuccessUrl("/");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
