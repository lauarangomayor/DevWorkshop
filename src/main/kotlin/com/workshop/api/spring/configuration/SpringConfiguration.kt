package com.workshop.api.spring.configuration

import com.workshop.api.spring.filter.JWTAuthorizationFilter;
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@EnableWebSecurity
@Configuration
class SpringConfiguration : WebSecurityConfigurerAdapter() {
    override fun configure(httpSecurity: HttpSecurity) {
        httpSecurity
        .cors()
        .and()
        .csrf().disable()
        .antMatcher("api/**") // Public routes
        .authorizeRequests()
        .anyRequest()
        .authenticated()
        .and()
        .addFilterAfter(JWTAuthorizationFilter(), UsernamePasswordAuthenticationFilter::class.java)
        .authorizeRequests()
    }
}
