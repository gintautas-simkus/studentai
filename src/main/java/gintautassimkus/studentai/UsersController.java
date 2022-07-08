package gintautassimkus.studentai;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;

import net.bytebuddy.dynamic.DynamicType.Builder.FieldDefinition.Optional;

@Controller
public class UsersController {
	@Autowired
	protected CustomUserService userService;

	@GetMapping("/register")
	public String register(WebRequest request, Model model) {
		UserDto userDto = new UserDto();
	    model.addAttribute("user", userDto);
	    return "users/registration";
	}

	@PostMapping("/register")
	public ModelAndView registerUserAccount(@ModelAttribute("user") @Valid UserDto userDto,
			  HttpServletRequest request, Errors errors) {
	    try {
	        userService.registerNewUserAccount(userDto);
	    } catch (UserAlreadyExistsException uaeEx) {
	    	System.out.println("EXCEPTION CAUGHT");        //mav.addObject("message", "An account for that username/email already exists.");
	        //return mav;
	    }

	    return new ModelAndView("users/registration", "user", userDto);
	}

	@GetMapping("/login")
	public String login(Model model)
	{		
		return "users/login";
	}
	
	@GetMapping("/")
	public String home() {
		return "users/home";
	}
}
