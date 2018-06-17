package pl.java.ebookcase.config;

import lombok.AllArgsConstructor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import pl.java.ebookcase.security.UserDetailsServiceImpl;
import pl.java.ebookcase.service.UserService;

@AllArgsConstructor
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/", "/main","/index","/register","/webjars/**","/h2-console/**","/css/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successForwardUrl("/login/success")
                .failureForwardUrl("/login/failure")
                .and()
                .logout()
                .logoutUrl("/logout");

        ///// needed for h2-console access
        http.csrf().disable();
        http.headers().frameOptions().disable();
        /////
    }

    @Override
    public UserDetailsService userDetailsServiceBean() throws Exception {
        return new UserDetailsServiceImpl(userService);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("user").password(bCryptPasswordEncoder.encode("user")).roles("USER")
                .and()
                .withUser("admin").password(bCryptPasswordEncoder.encode("admin")).roles("ADMIN");
        auth
                .userDetailsService(userDetailsServiceBean())
                .passwordEncoder(bCryptPasswordEncoder);
    }


}
