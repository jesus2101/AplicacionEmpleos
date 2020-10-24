package net.bavval.empleosMYSQL.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bavval.empleosMYSQL.model.Categoria;
import net.bavval.empleosMYSQL.service.IntCategoriasService;
import org.springframework.beans.factory.annotation.Qualifier;



@Controller
/*Anotacion Request Mapping a nivel de clase*/
@RequestMapping(value="/categorias")
public class CategoriasController {
     
	@Autowired
    @Qualifier("categoriasServiceJpa")
	private IntCategoriasService servicecategorias;
	
	@GetMapping("/crear")	
	public String crear(Categoria categoria) {
		
		return "categorias/formCategoria";
	}
	
	@GetMapping("/index")
	public  String mostrarIndex(Model model) {
		
		List<Categoria> list=servicecategorias.obtenerTodo();
		model.addAttribute("categoria", list);
		return "categorias/ListaCategorias";
	}
	
	@PostMapping("/guardar")
	public String guardar(Categoria categoria, BindingResult result, RedirectAttributes attributes) {
		
		if (result.hasErrors()) {
			for(ObjectError error:result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}	
			return "categorias/formCategoria";
		
		}
		 Integer ida=servicecategorias.obtenerTodo().size()+1;
		categoria.setId(ida);
		attributes.addFlashAttribute("msg", "Registro Guardado con exito!!!!");
		servicecategorias.guardar(categoria);
		
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idCategoria,RedirectAttributes attributes) {
		System.out.println("Categoria eliminada: "+idCategoria);
		servicecategorias.eliminar(idCategoria);
		attributes.addFlashAttribute("msg", "Categoria Eliminada");
		return "redirect:/categorias/index";
	}
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") int idCategoria,Model model){
		
		System.out.println("Vacnate modificada:"+idCategoria);
		Categoria categoria=servicecategorias.ObtenerPorId(idCategoria);
		model.addAttribute("categoria", categoria);
		return "categorias/formCategoria"; 
	}
	//Anotacion RequestMapping a nivel del metodo
	/*
	@RequestMapping (value="/index", method=RequestMethod.GET)
	public String mostrarIndex(Model model) {
		
		return "categorias/ListaCategorias";
	}
	
	@RequestMapping (value="/crear", method=RequestMethod.GET)
	public String crear() {
		
		return "categorias/formCategoria";
		
	}
	
	@RequestMapping (value="/guardar", method=RequestMethod.POST)
	public String guardar(@RequestParam("nombre") String nombre, @RequestParam("descripcion") String descripcion, Model model) {
		System.out.println(nombre);
		model.addAttribute("nombre", nombre);
		model.addAttribute("descripcion", descripcion);
		
		return "categorias/ListaCategorias";
		
	}
	*/
	
	
	
}
