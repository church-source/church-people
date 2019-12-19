package org.churchsource.churchpeople.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
      .csrf().disable()
      .cors().disable()
      .authorizeRequests()
      .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()//allow CORS option calls
      .antMatchers("/resources/**").permitAll()
      .anyRequest().authenticated()
      .and()
        .httpBasic()
      .and()
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
        .requestCache().disable();
  }

  @Override
  public void configure(WebSecurity web) throws Exception {
      web.ignoring().antMatchers(HttpMethod.OPTIONS, "/**");
  }

  @Autowired
  public void configureGlobal(AuthenticationManagerBuilder auth)
          throws Exception
  {
    auth.inMemoryAuthentication()
            .withUser("admin")
            .password("password")
            .roles("USER");
  }
}