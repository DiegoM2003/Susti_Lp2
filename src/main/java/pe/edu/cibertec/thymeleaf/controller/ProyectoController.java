package pe.edu.cibertec.thymeleaf.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import pe.edu.cibertec.web.model.User;
import pe.edu.cibertec.web.repository.IPersonRepository;
import pe.edu.cibertec.web.repository.IRoleRepository;
import pe.edu.cibertec.web.repository.IUserRepository;
import pe.edu.cibertec.web.service.UserService;

@Controller
public class ProyectoController {
	
	@Autowired
	private IRoleRepository repos;
	
	@Autowired
	private IPersonRepository reposPerson;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/greeting")
	public String greeting (@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
	}
	
	@GetMapping("/listar")
	public String listRole(Model model) {
		try {
			model.addAttribute("ltsRole", repos.findAll());
			model.addAttribute("ltsPerson", reposPerson.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "listado";
	}
	
	@GetMapping("/login")
	public String loginView(Model model) {
	    model.addAttribute("userLogin", new User());
	    return "login"; // Retorna la vista "login.html" desde templates
	}

	
	


	@GetMapping("/register")
	public String showRegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "register";
	}

	@PostMapping("/register")
	public String registerUser(@ModelAttribute User user) {
		userService.registerUser(user);
		return "redirect:/login";
	}
	
	

}
