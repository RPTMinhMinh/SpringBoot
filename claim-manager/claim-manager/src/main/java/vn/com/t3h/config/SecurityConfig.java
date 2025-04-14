package vn.com.t3h.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import vn.com.t3h.utils.Constant;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.csrf(AbstractHttpConfigurer::disable)
        .authorizeHttpRequests(request -> {
          request.requestMatchers("/cms/**").hasAnyRole("ADMIN")
              .requestMatchers("/", "/home", "/login", "/logout", "/process-login").permitAll()
              .requestMatchers(
                  "/assets/**", "/fonts/**", "/homeguest_files/**",
                  "/js/**", "/libs/**", "/loginmetlife/**",
                  "/page404/**", "/scss/**", "/tasks/**", "/css/**", "/images/**", "/cms-rs/**",
                  "/file/**").permitAll()
              .requestMatchers("/resource/**").permitAll()
              .requestMatchers("/kaira/**").permitAll()
              .anyRequest().authenticated();
        })
        .formLogin(
            form ->
                form.loginPage("/login")
                    .loginProcessingUrl(
                        "/process_login") //url để gửi username và password lên cho server
                    .defaultSuccessUrl("/process-after-login-success",
                        true)  //điều hướng khi login thành công theo role, VD: Admin -> /cms/dashboard, User -> /home
                    .failureUrl("/login?error=true")
        )
        .logout(
            logout ->
                logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
        );


    return http.build();
  }

  public static void main(String[] args) {
    PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    System.out.println("Password admin : " + passwordEncoder.encode("admin"));
    System.out.println("Password user : " + passwordEncoder.encode("user"));
  }
}
