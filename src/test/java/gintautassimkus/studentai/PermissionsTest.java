// https://spring.io/guides/gs/testing-web/
// https://stackoverflow.com/questions/15203485/spring-test-security-how-to-mock-authentication
// https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/web/servlet/result/StatusResultMatchers.html#is3xxRedirection--

package gintautassimkus.studentai;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
// https://spring.io/guides/gs/testing-web/
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;

// https://spring.io/guides/gs/testing-web/
import static org.hamcrest.Matchers.containsString;
// https://stackoverflow.com/questions/61776628/assertthat-how-to-invert-containsstring
import static org.hamcrest.Matchers.not;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import org.springframework.security.web.csrf.CsrfToken;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.*;

@SpringBootTest//(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class PermissionsTest {
	@Autowired
    private MockMvc mockMvc;
	
	@Mock
	private StudentaiRepository studentaiRepository;
	
	@Test
	public void testPermissionsForAnonymous() throws Exception {
		this.mockMvc.perform(get("/login")).andExpect(status().isOk()).
			andDo(print()).andExpect(content().string(containsString("Prisijungimas")));
		this.mockMvc.perform(get("/studentai")).andExpect(status().is3xxRedirection());
		this.mockMvc.perform(get("/dalykai")).andExpect(status().is3xxRedirection());
		this.mockMvc.perform(get("/registracija")).andExpect(status().is3xxRedirection());
		this.mockMvc.perform(get("/pazymiai")).andExpect(status().is3xxRedirection());
	}
	
	@Test
	@WithUserDetails("informacija")
	public void testPermissionsForUserRole() throws Exception {
		// GET's
		this.mockMvc.perform(get("/studentai")).andExpect(status().isOk()).
			andDo(print()).andExpect(content().string(not(containsString("Trinti")))).
			andExpect(content().string(not(containsString("<form"))));
		this.mockMvc.perform(get("/dalykai")).andExpect(status().isOk()).
			andDo(print()).andExpect(content().string(not(containsString("Trinti")))).
			andExpect(content().string(not(containsString("<form"))));
		this.mockMvc.perform(get("/registracija")).andExpect(status().isOk()).
			andDo(print()).andExpect(content().string(not(containsString("Išregistruoti")))).
			andExpect(content().string(not(containsString("<form"))));
		this.mockMvc.perform(get("/pazymiai")).andExpect(status().isOk()).
			andDo(print()).andExpect(content().string(not(containsString("Trinti")))).
			andExpect(content().string(not(containsString("<form"))));
		
		// POST's
		Studentas studentas = new Studentas("TTT", "UUU");
		Mockito.lenient().when(studentaiRepository.save(studentas)).thenReturn(studentas);
		this.mockMvc.perform(post("/studentai").
			param("vardas", "TTT").
			param("pavarde", "UUU").
			with(csrf())).andExpect(status().isForbidden());
		Mockito.lenient().doNothing().when(studentaiRepository).deleteById(1L);
		this.mockMvc.perform(post("/studentai/delete").
				param("id", "1").
				with(csrf())).andExpect(status().isForbidden());
/*		
		Studentas studentas = new Studentas("Jonas", "Jonaitis");
		Mockito.lenient().when(studentaiRepository.save(studentas)).thenReturn(studentas);
		this.mockMvc.perform(post("/studentai").
			param("vardas", "Jonas").
			param("pavarde", "Jonaitis").
			with(csrf())).andExpect(status().isForbidden());
		
/*
		this.mockMvc.perform(get("/dalykai")).andExpect(status().isOk()).
			andDo(print()).andExpect(content().string(not(containsString("Trinti")))).
			andExpect(content().string(not(containsString("<form"))));
		this.mockMvc.perform(get("/registracija")).andExpect(status().isOk()).
			andDo(print()).andExpect(content().string(not(containsString("Išregistruoti")))).
			andExpect(content().string(not(containsString("<form"))));
		this.mockMvc.perform(get("/pazymiai")).andExpect(status().isOk()).
			andDo(print()).andExpect(content().string(not(containsString("Trinti")))).
			andExpect(content().string(not(containsString("<form"))));
*/		
		
	}
}