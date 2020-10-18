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

import pe.edu.upc.entity.Type_Exercise;
import pe.edu.upc.service.IType_ExerciseService;

@Controller
@RequestMapping("/types_exercise")

public class Type_ExerciseController {

	@Autowired
	private IType_ExerciseService teService;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@GetMapping("/new")
	public String newCategory(Model model) {
		model.addAttribute("Type_Exercise", new Type_Exercise());
		return "/type_Exercise/type_Exercise";
	}
	
	@PostMapping("/save")
	public String saveCategory(@Valid Type_Exercise type_Exercise, BindingResult result, Model model, SessionStatus status)
	throws Exception {
		
		if (result.hasErrors()) {
			return "/type_Exercise/type_Exercise";
		}
		else {
			int rpta = teService.insert(type_Exercise);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el tipo de ejercicio");
				return "/type_Exercise/type_Exercise";
			}
			else {
				model.addAttribute("mensaje", "Se guardo correctamente el tipo de ejercicio");
				status.setComplete();
			}
		}
		
		model.addAttribute("listType_Exercise", teService.list());
		
		return "/type_Exercise/listType_Exercise";
	}
	
	@GetMapping("/list")
	public String listType_Exercise(Model model) {
		try {
			model.addAttribute("type_Exercise", new Type_Exercise());
			model.addAttribute("listType_Exercise", teService.list());
		}
		catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/type_Exercise/listType_Exercise";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!= null && id > 0) {
				teService.delete(id);
				model.put("mensaje", "Se elimino el tipo de ejercicio correctamente");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo eliminar el tipo de ejercicio");
		}
		model.put("listType_Exercise", teService.list());
		return "/type_Exercise/listType_Exercise";
	}
	
}
