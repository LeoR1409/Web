package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Forum;
import pe.edu.upc.entity.Talk;
import pe.edu.upc.service.IForumService;
@Controller
@RequestMapping("/forums")


public class ForumController {

	@Autowired
	private IForumService foService;
	
	
	
	@GetMapping("/new")
	public String newForum(Model model) {
		model.addAttribute("forum", new Forum());
		return "/forum/RegistroForo";
	}
	
	@PostMapping("/save")
	public String saveCategory(@Valid Forum forum, BindingResult result, Model model, SessionStatus status)
	throws Exception {
		
		if (result.hasErrors()) {
			return "/forum/forum";
		}
		else {
			int rpta = foService.insert(forum);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el foro");
				return "/forum/forum";
			}
			else {
				model.addAttribute("mensaje", "Se guardo correctamente el foro");
				status.setComplete();
			}
		}
		
		model.addAttribute("listForum", foService.list());
		
		return "redirect:/forums/list";
	}
	
	@GetMapping("/list")
	public String listForums(Model model) {
		try {
			model.addAttribute("forum", new Forum());
			model.addAttribute("listForum", foService.list());
		}
		catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/forum/Foros";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(Map<String, Object> model, @PathVariable(value="id") Integer id) {
		try {
			if (id!= null && id > 0) {
				foService.delete(id);
				model.put("mensaje", "Se elimino el foro correctamente");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo eliminar el foro");
		}
		model.put("listForum", foService.list());
		return "redirect:/forums/list";
	}
	
	@RequestMapping("/ingresar/{column}")
	public String ingresar(Map<String, Object> model, @PathVariable(value="column") String column) {
		try {
			String cadena="/forum/"+column;
			return cadena;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		model.put("listForums", foService.list());
		return "/forum/Foros";
	}
}
