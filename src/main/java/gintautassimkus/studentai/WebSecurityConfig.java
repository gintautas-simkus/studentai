package gintautassimkus.studentai;

import java.util.ArrayList;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public MyUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/index.js", "/index.css", "/register").permitAll()
				.antMatchers(HttpMethod.POST, "/register").permitAll()
				.antMatchers("/studentai/delete", "/dalykai/delete", "/registracija/delete", "/pazymiai/delete")
				.access("hasRole('ADMIN')")
				// https://stackoverflow.com/questions/7347183/using-spring-security-how-can-i-use-http-methods-e-g-get-put-post-to-disti
				.antMatchers(HttpMethod.POST, "/studentai", "/dalykai", "/registracija", "/pazymiai")
				.access("hasRole('ADMIN')")
				.anyRequest().authenticated()
				.and()
			.formLogin()
				// https://www.javadevjournal.com/spring/spring-security-success-handler/
				.defaultSuccessUrl("/", true)
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();
	}

	@Bean
	public DaoAuthenticationProvider authProvider() {
	    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
	    authProvider.setUserDetailsService(userDetailsService);
	    authProvider.setPasswordEncoder(encoder());
	    return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) {
	    auth.authenticationProvider(authProvider());
	}

	@Bean
	public PasswordEncoder encoder() {
	    return new BCryptPasswordEncoder();
	}
}