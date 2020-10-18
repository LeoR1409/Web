package pe.edu.upc.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;

import pe.edu.upc.entity.Editorial;
import pe.edu.upc.service.IEditorialService;

@Controller
@RequestMapping("/editoriales")
public class EditorialController {
	
	@Autowired
	private IEditorialService edService;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@GetMapping("/new")
	public String newEditorial(Model model) {
		model.addAttribute("editorial", new Editorial());
		return "/editorial/editorial";
	}
	
	@PostMapping("/save")
	public String saveCategory(@Valid Editorial editorial, BindingResult result, Model model, SessionStatus status)
	throws Exception {
		
		if (result.hasErrors()) {
			return "/editorial/editorial";
		}
		else {
			int rpta = edService.insert(editorial);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe la editorial");
				return "/editorial/editorial";
			}
			else {
				model.addAttribute("mensaje", "Se guardo correctamente la editorial");
				status.setComplete();
			}
		}
		
		model.addAttribute("listEditoriales", edService.list());
		
		return "/editorial/listEditoriales";
	}
	
	@GetMapping("/list")
	public String listEditoriales(Model model) {
		try {
			model.addAttribute("editorial", new Editorial());
			model.addAttribute("listEditoriales", edService.list());
		}
		catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/editoriales/listEditoriales";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!= null && id > 0) {
				edService.delete(id);
				model.put("mensaje", "Se elimino la editorial correctamente");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo eliminar la editorial");
		}
		model.put("listEditoriales", edService.list());
		return "/editoriales/listEditoriales";
	}
	
	
	
	
	
}
