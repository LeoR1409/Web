package pe.edu.upc.controller;

import java.text.ParseException;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import pe.edu.upc.entity.DetailsForum;
import pe.edu.upc.entity.Forum;
import pe.edu.upc.service.ICommentsService;
import pe.edu.upc.service.IDetailsForumService;
import pe.edu.upc.service.IForumService;
@Controller
@RequestMapping("/forums")


public class ForumController {

	@Autowired
	private IForumService foService;
	private ICommentsService commenService;
	private IDetailsForumService defoService;

	DetailsForum detailsforum;
	
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
	
	@RequestMapping("/ingresar/{column}/{id}")
	public String ingresar(Map<String, Object> model, @PathVariable(value="column") String column,@PathVariable(value="id") int id) {
		try {
			model.put("listComments", commenService.list());
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo eliminar el foro");
		}
		String cadena="/forum/"+column;
		return cadena;
	}
	@RequestMapping("/modificar/{id}")
	public String modificar(@PathVariable int id, Model model, RedirectAttributes objRedir) 
			throws ParseException
			{
				Optional<DetailsForum> objDetailsForum = defoService.listarId(id);
				
				if (objDetailsForum == null) {
					objRedir.addFlashAttribute("mensaje", "Ocurrio un rochesin");
					return "redirect:/detailsforum/list";
				}
				else {
					model.addAttribute("detailsforum", objDetailsForum);
					return "/detailsforum/ModificarForo";
				}
			}
	@GetMapping("/newdetails/{id}")
	public String newDetailsForum(Model model,@PathVariable(value="id") int id) {
		Optional<Forum> fo = foService.listarId(id);
		model.addAttribute("detailsforum", new DetailsForum());
		return "/forum/ModificarForo";
	}
}
