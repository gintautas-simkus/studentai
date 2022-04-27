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
	@Autowired
	private StudentaiRepository studentaiRepository;
	@Autowired
	private DalykaiRepository dalykaiRepository;

	@GetMapping("")
	public String index(Model model)
	{		
		model.addAttribute("studentaiDalykai", studentaiDalykaiRepository.findAll());
		model.addAttribute("studentai", studentaiRepository.findAll());
		model.addAttribute("dalykai", dalykaiRepository.findAll());
		return "registracija/index";
	}
	
	@PostMapping("")
	public String create(@RequestParam(name = "studentas_id", required = true) Long studentas_id,
			@RequestParam(name = "dalykas_id", required = true) Long dalykas_id) throws Exception
	{
		StudentasDalykas sd = new StudentasDalykas(studentas_id, dalykas_id);
		studentaiDalykaiRepository.save(sd);
		return "redirect:/registracija";
	}

	@PostMapping("delete")
	public void delete(@RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response)
	{
		studentaiDalykaiRepository.deleteById(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}