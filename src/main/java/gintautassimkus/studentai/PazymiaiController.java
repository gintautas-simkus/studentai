package gintautassimkus.studentai;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
@RequestMapping("pazymiai")
public class PazymiaiController {
	@Autowired
	private PazymiaiRepository pazymiaiRepository;
	@Autowired
	private StudentaiDalykaiRepository studentaiDalykaiRepository;

	@GetMapping("")
	public String index(Model model)
	{		
		model.addAttribute("pazymiai", pazymiaiRepository.findAll());
		model.addAttribute("studentaiDalykai", studentaiDalykaiRepository.findAll());
		return "pazymiai/index";
	}
	
	@PostMapping("")
	public String create(@RequestParam(name = "studentas_dalykas_id", required = true) Long id,
			@RequestParam(name = "pazymys", required = true) int pazymys,
			@RequestParam(name = "komentaras") String komentaras) throws Exception
	{
		StudentasDalykas studentasDalykas = studentaiDalykaiRepository.findById(id).get();
		Pazymys paz = new Pazymys(studentasDalykas, pazymys, komentaras);
		pazymiaiRepository.save(paz);
		return "redirect:/pazymiai";
	}
	
	@GetMapping(value = "{id}/edit")
	public String edit(@PathVariable Long id, Model model)
	{
		java.util.Optional<Pazymys> p = pazymiaiRepository.findById(id);
		Pazymys pazymys = null;
		if (!p.isEmpty()) {
			pazymys = p.get();
		}
		model.addAttribute("pazymys", pazymys);
		return "pazymiai/_edit";
	}
	
	// NOTE: @PutMapping(value = "/employees/{id}")
	// https://howtodoinjava.com/spring-boot2/testing/spring-boot-mockmvc-example/
	@PostMapping("delete")
	public void delete(@RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response)
	{
		pazymiaiRepository.deleteById(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}