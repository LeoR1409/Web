package pe.edu.upc.controller;
import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.Users;
import pe.edu.upc.service.ITypeUserService;
import pe.edu.upc.service.IUserService;

@Controller
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private IUserService cS;
	@Autowired
	private ITypeUserService rS;
	
	@GetMapping("/new")
	public String newAccount(Model model) {
		model.addAttribute("users", new Users());

		model.addAttribute("listtype", rS.list());
		return "user/user";
	}

	@PostMapping("/save")
	public String saveAccount(@Validated Users users, BindingResult result, Model model) throws Exception {
		if (result.hasErrors()) {
			model.addAttribute("roles", rS.list());
			return "user/user";
		} else {
			String password = new BCryptPasswordEncoder().encode(users.getPassword());
			users.setPassword(password);;
			int rpta = cS.insert(users);
			if (rpta > 0) {
				model.addAttribute("listtype", rS.list());
				model.addAttribute("mensaje2", "El el correo ya está(n) en uso");
				return "user/user";
			} else {

				cS.insert(users);
				model.addAttribute("roles", rS.list());
				model.addAttribute("listAccounts", cS.list());
				return "login";
			}
		}
	}
}
