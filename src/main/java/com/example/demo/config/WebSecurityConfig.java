package com.example.demo.config;

import com.example.demo.constant.ERole;
import com.example.demo.security.UserDetailsServiceImpl;
import com.example.demo.security.jwt.AuthenEntryPointJwt;
import com.example.demo.security.jwt.authenTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
/*allow spring to find and automatically apply the class to the global web security */
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
/*provides APO security on methods */
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @Autowired
    private AuthenEntryPointJwt unauthorizedHandler;

    @Bean
    public authenTokenFilter authenticationJwtTokenFilter() {
        return new authenTokenFilter();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
        /* this authentication needs to be through connecting to JPA and getting data from database  */
        /* spring security will call userDetailsServiceImpl to get the user information  every time there is an authentication */
    }

    /* authorization*/
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()

                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // do not create a session
                .authorizeRequests().antMatchers("/api/auth/**").permitAll()
                .antMatchers("/api/test/**").permitAll()
                .antMatchers("/login/**").permitAll()
                .antMatchers("/**").permitAll()
                .antMatchers("/admin").hasRole(ERole.ADMIN.toString())
                .antMatchers("/user").hasRole(ERole.USER.toString())
                .anyRequest().authenticated();
        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
        // adding filter before authentication
    }
}
/* define payloads for spring rest
Request:
loginRequest:{username , password}
signUpRequest:{username,email,password}
Response:
JWTResponse : {token,type,id,username,email,roles}
MessageResponse:{message}
* */
