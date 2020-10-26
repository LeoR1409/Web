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

import pe.edu.upc.entity.Book;
import pe.edu.upc.service.IBook_Service;
import pe.edu.upc.service.IEditorialService;

@Controller
@RequestMapping("/books")
public class BookController {
	
	@Autowired
	private IBook_Service edService;
	@Autowired
	private IEditorialService ediService;
	
	
	
	@GetMapping("/new")
	public String newEditorial(Model model) {
		model.addAttribute("book", new Book());
		model.addAttribute("listEditorial", ediService.list());
		return "book/book";
	}
	
	@PostMapping("/save")
	public String saveCategory(@Valid Book book, BindingResult result, Model model, SessionStatus status)
	throws Exception {
		
		if (result.hasErrors()) {
			return "/book/book";
		}
		else {
			int rpta = edService.insert(book);
			if (rpta > 0) {
				model.addAttribute("mensaje", "Ya existe el libro");
				return "/book/book";
			}
			else {
				model.addAttribute("mensaje", "Se guardo correctamente el libro");
				status.setComplete();
			}
		}
		
		model.addAttribute("listBook", edService.list());
		model.addAttribute("listEditorial", ediService.list());
		
		return "redirect:/books/list";
	}
	
	@GetMapping("/list")
	public String listEditoriales(Model model) {
		try {
			model.addAttribute("book", new Book());
			model.addAttribute("listBook", edService.list());
		}
		catch(Exception e) {
			model.addAttribute("error", e.getMessage());
		}
		return "/book/listBook";
	}
	
	@RequestMapping("/delete")
	public String delete(Map<String, Object> model, @RequestParam(value="id") Integer id) {
		try {
			if (id!= null && id > 0) {
				edService.delete(id);
				model.put("mensaje", "Se elimino el libro correctamente");
		}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
			model.put("mensaje", "No se pudo eliminar el libro");
		}
		model.put("listBook", edService.list());
		return "/book/listBook";
	}
}
		
	
	
	
	
	
