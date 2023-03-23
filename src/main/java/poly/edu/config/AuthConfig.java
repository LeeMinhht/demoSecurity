package poly.edu.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {

    //Max hoá mật khẩu
    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager(){
        UserDetails user = User.withDefaultPasswordEncoder().username("user").password("123").roles("USER").build();
        UserDetails admin = User.withDefaultPasswordEncoder().username("admin").password("123").roles("USER").build();
        return new InMemoryUserDetailsManager(user,admin);
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws  Exception{
        return  http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth ->{
                    auth.anyRequest().authenticated();
                }).httpBasic(Customizer.withDefaults())
                .build();
    }


    //Quản lý dữ liệu người sử dụng
//    @Bean
//    public SecurityFilterChain configure(AuthenticationManagerBuilder auth) throws Exception{
//        auth.inMemoryAuthentication()
//                .withUser("user1").password(pe.encode("123")).roles("GUES").and()
//                .withUser("user2").password(pe.encode("123")).roles("USER","GUES").and()
//                .withUser("user3").password(pe.encode("123")).roles("ADMIN","USER","GUES");
//        return (SecurityFilterChain) auth.build();
//    }



}
