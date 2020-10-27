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

import pe.edu.upc.entity.Talk;
import pe.edu.upc.service.ITalkService;

@Controller
@RequestMapping("/talks")

public class TalkController {

	@Autowired
	private ITalkService tlService;
	
	
	
	@GetMapping("/new")
	public String newtalk(Model model) {
		model.addAttribute("talk", new Talk());
		return "talk/talk";
	}
	
	@PostMapping("/save")
	public String saveCategory(@Valid Talk talk, BindingResult result, Model model, SessionStatus status)
	throws Exception {
		
		if (result.hasErrors()) {
			return "/talk/talk";
		}
		else {
			int rpta = tlService.insert(talk);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe la charla");
				return "/editorial/editorial";
			}
			else {
				model.addAttribute("mensaje", "Se guardo correctamente la charla");
				status.setComplete();
			}
		}
		
		model.addAttribute("listTalk", tlService.list());
		
		return "/talk/listTalk";
	}
	
	@GetMapping("/list")
	public String listTalk(Model model) {
		try {
			model.addAttribute("talk", new Talk());
			model.addAttribute("listTalk", tlService.list());
		}
		catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/talk/listTalk";
	}
	
	@RequestMapping("/delete/{id}")
	public String delete(Map<String, Object> model, @PathVariable(value="id") Integer id) {
		try {
			if (id!= null && id > 0) {
				tlService.delete(id);
				model.put("mensaje", "Se elimino la charla correctamente");
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo eliminar la charla ");
		}
		model.put("listTalk", tlService.list());
		return "/talk/listTalk";
	}
}
