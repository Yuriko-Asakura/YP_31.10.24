//package com.example.pr3_bd.config;
//
//import com.example.pr3_bd.enity.ModelUser;
//import com.example.pr3_bd.repository.UserRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Lazy;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//
//@Configuration
//@EnableWebSecurity @EnableMethodSecurity(prePostEnabled = true)
//public class WebSecurityConfig {
//    private final UserRepository userRepository;private final PasswordEncoder passwordEncoder;
//    @Autowired
//    public WebSecurityConfig(@Lazy UserRepository userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(username -> {
//            ModelUser user = userRepository.findByUsername(username);
//            if (user == null) {
//                throw new UsernameNotFoundException("Такой пользователь не существует");
//            }
//            return new org.springframework.security.core.userdetails.User(
//                    user.getUsername(),
//                    user.getPassword(),
//                    user.isActive(),
//                    true,
//                    true,
//                    true,
//                    user.getRoles()
//            );
//        }).passwordEncoder(passwordEncoder);
//    }
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeRequests(authorize -> authorize .requestMatchers("/login", "/registration").permitAll()
//                        .anyRequest().authenticated())
//                .formLogin(form -> form .loginPage("/login")
//                        .defaultSuccessUrl("/")
//                        .permitAll())
//                .logout(logout -> logout.permitAll())
//                .csrf(csrf -> csrf.disable())
//                .cors(cors -> cors.disable());
//
//        return http.build();
//    }
//}
