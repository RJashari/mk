/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

/*
 *
 * @author rinor.jashari
 */
@Configuration
@EnableWebSecurity
public class WebSecuriityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    @Qualifier("usersService")
    UserDetailsService userDetailsService;
 
    
        @Override
        public void configure(WebSecurity webSecurity) throws Exception {
            webSecurity.ignoring().antMatchers("/static/**");
        }
      
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        System.out.println("HINI NKONFIGURIM");
        http.authorizeRequests()
                .antMatchers("/styles.css").permitAll()
                .antMatchers("/bpblogo.png").permitAll()
                .antMatchers("/login").permitAll()
                .antMatchers("/bpbLogo.png").permitAll()
                .antMatchers("/bpbIcon1.png").permitAll()
                .antMatchers("/logoBpB.png").permitAll()
                .antMatchers("/loginStyle.css").permitAll()
                .antMatchers("/bpbfav.png").permitAll()
                .antMatchers("/users/add/**").permitAll()
                .anyRequest().authenticated()
                .and().formLogin()
                .loginPage("/login").failureUrl("/login?error")
                .defaultSuccessUrl("/home")
                .usernameParameter("username")
                .passwordParameter("password")
                .and().logout().logoutSuccessUrl("/login?logout")
                .and().csrf()
                .and().exceptionHandling().accessDeniedPage("/403");
    }
    
    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        System.out.println("HINI NKONFIGURIM2");
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
        
    }
     
    @Bean(name="passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
