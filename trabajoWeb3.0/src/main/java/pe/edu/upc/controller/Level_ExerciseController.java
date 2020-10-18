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

import pe.edu.upc.entity.Level_Exercise;
import pe.edu.upc.service.ILevel_ExerciseService;


@Controller
@RequestMapping("/level_Exercise")

public class Level_ExerciseController {

	@Autowired
	private ILevel_ExerciseService leService;
	
	@RequestMapping("/index")
	public String irWelcome() {
		return "welcome";
	}
	
	@GetMapping("/new")
	public String newLevel_Exercise(Model model) {
		model.addAttribute("level_Exercise", new Level_Exercise());
		return "/level_Exercise/level_Exercise";
	}
	
	@PostMapping("/save")
	public String saveCategory(@Valid Level_Exercise level_Exercise, BindingResult result, Model model, SessionStatus status)
	throws Exception {
		
		if (result.hasErrors()) {
			return "/level_Exercise/level_Exercise";
		}
		else {
			int rpta = leService.insert(level_Exercise);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el nivel del ejercicio");
				return "/level_Exercise/level_Exercise";
			}
			else {
				model.addAttribute("mensaje", "Se guardo correctamente el nivel del ejercicio");
				status.setComplete();
			}
		}
		
		model.addAttribute("listLevel_Exercise", leService.list());
		
		return "/level_Exercise/listLevel_Exercise";
	}
	
	@GetMapping("/list")
	public String listLevel_Exercise(Model model) {
		try {
			model.addAttribute("category", new Level_Exercise());
			model.addAttribute("listCategories", leService.list());
		}
		catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/level_Exercise/listLevel_Exercise";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!= null && id > 0) {
				leService.delete(id);
				model.put("mensaje", "Se elimino el nivel de ejercicio correctamente");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo eliminar el nivel del ejercicio");
		}
		model.put("listLevel_Exercise", leService.list());
		return "/level_Exercise/listLevel_Exercise";
	}
	
}
