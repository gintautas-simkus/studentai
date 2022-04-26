package gintautassimkus.studentai;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("registracija")
public class StudentaiDalykaiController {
	@Autowired
	private StudentaiDalykaiRepository studentaiDalykaiRepository;

	@GetMapping("")
	public String index(Model model)
	{		
		model.addAttribute("studentaiDalykai", studentaiDalykaiRepository.findAll());
		return "registracija/index";
	}
	
/*
	@PostMapping("")
	public String create(@RequestParam(name = "vardas", required = true) String vardas,
			@RequestParam(name = "pavarde", required = true) String pavarde) throws Exception
	{
		Studentas studentas = new Studentas(vardas, pavarde);
		studentaiRepository.save(studentas);
		return "redirect:/studentai";
	}
	
	@PostMapping("delete")
	public void delete(@RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response)
	{
		studentaiRepository.deleteById(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
*/
}