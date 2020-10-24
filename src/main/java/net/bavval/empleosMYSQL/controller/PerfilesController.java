package net.bavval.empleosMYSQL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import net.bavval.empleosMYSQL.model.Perfil;

import net.bavval.empleosMYSQL.service.IntPerfilesService;

@Controller
@RequestMapping(value="/perfiles")
public class PerfilesController {
	@Autowired
    private IntPerfilesService servicePerfiles;
	
	@GetMapping("/crear")	
	public String crear() {
		
		return "perfiles/formPerfiles";
	}
	
	@GetMapping("/index")
	public  String mostrarIndex(Model model) {
		
		List<Perfil> list=servicePerfiles.obtenerTodo();
		model.addAttribute("perfil", list);
		return "perfiles/listaPerfiles";
	}
	
	@PostMapping("/guardar")
	public String guardar(Perfil perfil, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			for(ObjectError error:result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}	
			return "perfiles/formPerfiles";
		
		}
		
		attributes.addFlashAttribute("msg", "Registro Guardado con exito!!!!");
		servicePerfiles.guardar(perfil);
		
		return "redirect:/perfiles/index";
	}
	
	
	
}
