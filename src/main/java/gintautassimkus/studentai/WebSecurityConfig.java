package gintautassimkus.studentai;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.mysql.cj.x.protobuf.MysqlxCrud.Collection;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/index.js", "/index.css").permitAll()
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
	@Override
	public UserDetailsService userDetailsService() {
		UserDetails info =
			 User.withDefaultPasswordEncoder()
				.username("informacija")
				.password("passwordi")
				.roles("USER")
				.build();

		UserDetails admin =
				 User.withDefaultPasswordEncoder()
					.username("administracija")
					.password("passworda")
					.roles("USER", "ADMIN")
					.build();

		ArrayList<UserDetails> users = new ArrayList<UserDetails>();
		users.add(info);
		users.add(admin);

		return new InMemoryUserDetailsManager(users);
	}
}