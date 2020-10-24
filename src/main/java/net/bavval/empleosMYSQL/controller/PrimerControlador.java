package net.bavval.empleosMYSQL.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import net.bavval.empleosMYSQL.model.Perfil;
import net.bavval.empleosMYSQL.model.Usuario;
import net.bavval.empleosMYSQL.model.Vacante;
import net.bavval.empleosMYSQL.service.IntCategoriasService;
import net.bavval.empleosMYSQL.service.IntUsuariosService;
import net.bavval.empleosMYSQL.service.IntVacantesService;



@Controller
public class PrimerControlador {
	//inyectar una instancia de la clase de servicio
	//instancia mediante la interface
	@Autowired
	private IntVacantesService servicevacantes;
	
	@Autowired
	private IntUsuariosService serviceUsuarios;
	
	@Autowired
	private IntCategoriasService serviceCategorias;
	
	@GetMapping("/tabla")
	public String tabla(Model model) {
		List<Vacante> list=servicevacantes.obtenerTodo();
		model.addAttribute("list", list);
		return "tabla";
		
	}
	
	@GetMapping("/buscar")
	public String buscar(@ModelAttribute("search") Vacante vacante, Model model) {
		
		System.out.println("Consultando por: "+vacante);
		
		ExampleMatcher matcher=ExampleMatcher.matching().withMatcher("descripcion", ExampleMatcher.GenericPropertyMatchers.contains());
		
		
		Example<Vacante> example=Example.of(vacante,matcher);
		List<Vacante> lista=servicevacantes.buscarByExample(example);
		model.addAttribute("vacantes",lista);
		return "home";
	}
	
	@InitBinder
	public void InitBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
	}

	//@GetMapping("/detalle")
	@GetMapping("/d")
	public String detalle(Model model) {
	/*Vacante vac=new Vacante();
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	try {
		vac.setNombre("Ingeniero");
		vac.setDescripcion("Desarrollo de apps");
		//vac.setId(1);
		vac.setSalario(3500.21);
		vac.setFecha(sdf.parse("10/12/2019"));
	}catch(ParseException e){
		System.out.println("Error: "+e.getMessage());	
	}
	
	model.addAttribute("vacante", vac);
	return "detalle";*/
		model.addAttribute("vacantes", servicevacantes.buscarDestacadas());
		return "home";
		
	}
	@GetMapping("/")
    public String inicio() {
		//model.addAttribute("mensaje", "Inicio de empleos");
		/*String nombre="Ingeniero en sistemas";
		String descripcion="Desarrollo de aplicaciones web";
		Date fechaPub=new Date();
		double salario=13500;
		model.addAttribute("nombre", nombre);
		model.addAttribute("descripcion", descripcion);
		model.addAttribute("fecha", fechaPub);
		model.addAttribute("salario", salario);*/
		//List<Vacante> lista=servicevacantes.buscarDestacadas();
		//model.addAttribute("vacantes",lista);
		return "home";
	}
	
	@ModelAttribute
	public void setGenerico(Model model) {
		
		Vacante vacanteSearch=new Vacante();
		vacanteSearch.reset();
		model.addAttribute("vacantes",servicevacantes.buscarDestacadas());
		model.addAttribute("categorias",serviceCategorias.obtenerTodo());
		model.addAttribute("search",vacanteSearch);
	}
	
	@GetMapping("/registrate")
	public String registrarse(Usuario usuario) {
		return "formRegistro";
	}
	
	@PostMapping("/registrate")
	public String guardarRegistro(Usuario usuario, RedirectAttributes attributes) {
		usuario.setEstatus("1");
		usuario.setFechaRegistro(new Date());
		//El perfil del registro por default es "Uusario"
		//Creamos el perfil que le asignaremos al usuario nuevo
		Perfil perfil=new Perfil();
		perfil.setId(2);
		usuario.agregar(perfil);
		
		serviceUsuarios.guardar(usuario);
		attributes.addFlashAttribute("msg","Registro del usuario con exito");
		return "redirect:/usuarios/index";
		
	}
	
}
