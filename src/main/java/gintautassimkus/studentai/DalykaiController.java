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

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
@RequestMapping("dalykai")
public class DalykaiController {
	@Autowired
	private DalykaiRepository dalykaiRepository;

	@GetMapping("")
	public String index(Model model)
	{		
		model.addAttribute("dalykai", dalykaiRepository.findAll());
		return "dalykai/index";
	}
	
	@PostMapping("")
	public String create(@RequestParam(name = "pavadinimas", required = true) String pavadinimas) throws Exception
	{
		Dalykas dalykas = new Dalykas(pavadinimas);
		dalykaiRepository.save(dalykas);
		return "redirect:/dalykai";
	}
	
	@PostMapping("delete")
	public void delete(@RequestParam(name = "id", required = true) Long id,
			HttpServletResponse response)
	{
		dalykaiRepository.deleteById(id);
		response.setStatus(HttpServletResponse.SC_NO_CONTENT);
	}
}