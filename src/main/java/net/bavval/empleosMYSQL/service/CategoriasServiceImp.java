package net.bavval.empleosMYSQL.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.stereotype.Service;

import net.bavval.empleosMYSQL.model.Categoria;


@Service
public class CategoriasServiceImp implements IntCategoriasService  {
	
	private List<Categoria> lista=null;
	
	public CategoriasServiceImp() {
		lista=new LinkedList<Categoria>();
		SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
		try {
		Categoria cat1=new Categoria();
		cat1.setId(1);
		cat1.setNombre("Transporte");
		cat1.setDescripcion("Aqui van los emples de tranporte");
		
		Categoria cat2=new Categoria();
		cat2.setId(2);
		cat2.setNombre("Aviadores");
		cat2.setDescripcion("Manejan aviones");
		
		Categoria cat3=new Categoria();
		cat3.setId(3);
		cat3.setNombre("Barqueros");
		cat3.setDescripcion("Aqui van los emples de barcos");
		
		Categoria cat4=new Categoria();
		cat4.setId(4);
		cat4.setNombre("Docencia");
		cat4.setDescripcion("Aqui van los maestros");
		
		Categoria cat5=new Categoria();
		cat5.setId(5);
		cat5.setNombre("Cocineros");
		cat5.setDescripcion("Aqui van los cocineros");
		
		Categoria cat6=new Categoria();
		cat6.setId(6);
		cat6.setNombre("Bloggers");
		cat6.setDescripcion("Aqui van los bloggers");
		
		lista.add(cat1);
		lista.add(cat2);
		lista.add(cat3);
		lista.add(cat4);
		lista.add(cat5);
		lista.add(cat6);
			
		}catch(Exception e){
			System.out.println("Error: "+e.getMessage());
		}
		
	}

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		lista.add(categoria);
		
	}

	@Override
	public List<Categoria> obtenerTodo() {
		// TODO Auto-generated method stub
		return lista;
	}

	@Override
	public Categoria ObtenerPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		for(Categoria c:lista) {
			if(c.getId()==idCategoria) {
				return c;
			}
		}
		return null;
	}

	@Override
	public void eliminar(Integer idCategoria) {
		// TODO Auto-generated method stub
		
	}

	

}
