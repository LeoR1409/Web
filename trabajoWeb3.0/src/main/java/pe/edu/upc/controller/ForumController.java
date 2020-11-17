package pe.edu.upc.controller;

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

import pe.edu.upc.entity.Comments;
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
			//Optional<Forum> fo = foService.listarId(id);
			//model.put("forum", fo.get());
			//model.put("listComments", commenService.list());
			//model.put("comments", new Comments());
			//model.put("detailsforum", new DetailsForum());
			String cadena="/forum/"+column;
			return cadena;
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		model.put("listForums", foService.list());
		return "/forum/Foros";
	}
	@RequestMapping("/savecomments{id}")
    public String newProductXImportation(@PathVariable(value = "id") int id, @Valid DetailsForum detailsforum,
            RedirectAttributes flash, BindingResult result, Model model, SessionStatus status) {
		Optional<Forum> fo = foService.listarId(id);
        if (result.hasErrors()) {
            flash.addFlashAttribute("error", "El valor debe ser positivo");
            String cadena1 = "redirect:/loan/newexemplary/" + id;
            return cadena1;
        }
        try {
            Forum foru = new Forum();
            foru = fo.get();
            foru.setIdForums(id);
            detailsforum.setForum(foru);
            fo.get().addDetailImportation(detailsforum);
            foService.insert(fo.get());
            status.isComplete();
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            System.out.println(e.getMessage());
        }
        String cadena = "redirect:/forums/ingresar/" + fo.get().getTopic()+ "/" + id;
        return cadena;
    }
}
