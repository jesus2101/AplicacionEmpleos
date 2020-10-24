package net.bavval.empleosMYSQL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bavval.empleosMYSQL.model.Usuario;
import net.bavval.empleosMYSQL.service.IntUsuariosService;

@Controller
@RequestMapping("/usuarios")
public class UsuariosController {
	
	
	@Autowired
	private IntUsuariosService serviceUsuarios;
	
	@GetMapping("/index")
	public String mostrarIndex(Model model) {
		List<Usuario> usuarios=serviceUsuarios.buscarTodos();
		model.addAttribute("usuarios", usuarios);		
		return "usuarios/listUsuarios";
	}
	
	@GetMapping("/delete/{id}")
	public String eliminar(@PathVariable("id") int idUsuario,RedirectAttributes attributes) {
		
		serviceUsuarios.eliminar(idUsuario);
		attributes.addFlashAttribute("msg", "Usuario Eliminado");
		return "redirect:/usuarios/index";
	}
	
	

}
