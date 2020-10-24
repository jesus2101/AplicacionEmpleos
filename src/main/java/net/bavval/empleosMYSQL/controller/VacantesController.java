package net.bavval.empleosMYSQL.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bavval.empleosMYSQL.model.Vacante;
import net.bavval.empleosMYSQL.service.IntCategoriasService;
import net.bavval.empleosMYSQL.service.IntVacantesService;
import net.bavval.empleosMYSQL.util.Utileria;



@Controller
@RequestMapping(value="/vacantes")
public class VacantesController {
      
	@Autowired
	private IntVacantesService servicevacantes;
	
	@Autowired
	@Qualifier("categoriasServiceJpa")
	private IntCategoriasService servicecategorias;
	
	@GetMapping("/crear")	
	public String crear(Vacante vacante,Model model) {
		
		model.addAttribute("categorias", servicecategorias.obtenerTodo());
		
		
		return "vacantes/formVacante";
	}
	
	//Para vincular un parametro(binding) de una peticin http a una variable de un metodo definido
	//en el controlador de cuaquier tipo(int,double Date)
	//Por default todos los parametros son cadenos
	
	
	
	@GetMapping("/vista/{id}")
	public String verDetalle(@PathVariable("id") int idVacante,Model model) {
		//System.out.println("IdVacnate: "+idVacante);
		Vacante vacante=servicevacantes.obtenerVacantePorId(idVacante);
		//model.addAttribute("idVacante", idVacante);
		//System.out.println("Vacante = "+vacante);
		model.addAttribute("idVacante", vacante);
		return "detalle";
	}
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable("id") int idVacante,RedirectAttributes attributes){
		//http://localhost:9898/vacantes/eliminar?id=1
		//model.addAttribute("idVacante", idVacante);
		
		System.out.println("Vacnate eliminada:"+idVacante);
		servicevacantes.eliminar(idVacante);
		attributes.addFlashAttribute("msg", "Vacante Eliminada");
		return "redirect:/vacantes/index";
	}
	/*
	 @PostMapping("/guardar")	
	public String guardar() {
		System.out.println(vacante);
		return "vacantes/listaVacantes";
	}
	 
	 
	  */
	
	@GetMapping("/editar/{id}")
	public String editar(@PathVariable("id") int idVacante,Model model){
		//http://localhost:9898/vacantes/eliminar?id=1
		//model.addAttribute("idVacante", idVacante);
		
		System.out.println("Vacnate modificada:"+idVacante);
		Vacante vacante=servicevacantes.obtenerVacantePorId(idVacante);
		model.addAttribute("vacante",vacante);
		//model.addAttribute("categorias",servicecategorias.obtenerTodo());
				return "vacantes/formVacante";
	}
	
	@ModelAttribute
	public void setGenericos(Model model) {
		model.addAttribute("categorias",servicecategorias.obtenerTodo());
	}
	
	

	@GetMapping("/indexPaginado")
	public  String mostrarIndex(Model model, Pageable page) {
	
		Page<Vacante> list=servicevacantes.obtenerTodas(page);
		model.addAttribute("vacante", list);
		
		return "vacantes/listaVacantes";
	}
	
	@GetMapping("/index")
	public  String mostrarIndex(Model model) {
		
		List<Vacante> list=servicevacantes.obtenerTodo();
		model.addAttribute("vacante", list);
		
		return "vacantes/listaVacantes";
	}
	
	@PostMapping("/guardar")	
	public String guardar(Vacante vacante, BindingResult result, RedirectAttributes attributes, @RequestParam("archivoImagen") MultipartFile multiPart) {
		
		
		if (result.hasErrors()) {
			for(ObjectError error:result.getAllErrors()) {
				System.out.println("Ocurrio un error: "+error.getDefaultMessage());
			}	
			return "vacantes/formVacante";
			
		}
		
		//codigo para validar Imagen
		if(!multiPart.isEmpty()) {
			String ruta="c:/empleos/imagenes/";
			String nombreImagen=Utileria.guardarArchivo(multiPart, ruta);
			if(nombreImagen !=null){
				//Procesamos la variable nombreImagen
				vacante.setImagen(nombreImagen);
			}
			
		}
		attributes.addFlashAttribute("msg", "Registro Guardado con exito!!!!");
		servicevacantes.guardar(vacante);
		//attributes.addFlashAttribute("msg","Registro Guardado");
		return "redirect:/vacantes/index";
	}
	
	@InitBinder
	public void initBinder(WebDataBinder webDataBinder) {		
			SimpleDateFormat dateFormat=new SimpleDateFormat("dd-MM-yyyy");
			webDataBinder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat,false));
	}
	
	
	
	
}
