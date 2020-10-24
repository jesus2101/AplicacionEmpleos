package net.bavval.empleosMYSQL.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.LinkedList;
import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.bavval.empleosMYSQL.model.Vacante;


@Service
public class VacantesServiceImp implements IntVacantesService {
	
	private List<Vacante> lista=null;
public VacantesServiceImp() {
	lista=new LinkedList<Vacante>();
	SimpleDateFormat sdf=new SimpleDateFormat("dd/MM/yyyy");
	
	try {
		Vacante vac1=new Vacante();
		vac1.setNombre("Doctor");
		vac1.setDescripcion("Atender personal turno de la tarde");
		vac1.setSalario(1111.32);
		vac1.setFecha(sdf.parse("11/12/2019"));
		vac1.setImagen("empresa1.jpg");
		vac1.setDestacado(1);
		vac1.setEstatus("Aprobada");
		vac1.setId(1);
		
		
		Vacante vac2=new Vacante();
		vac2.setNombre("Jugador");
		vac2.setDescripcion("Jugador del madrid");
		vac2.setSalario(3232.32);
		vac2.setFecha(sdf.parse("11/08/2020"));
		vac2.setImagen("empresa2.jpg");
		vac2.setEstatus("Aprobada");
		//vac2.setDestacado(0);
		vac2.setId(2);
		
		Vacante vac3=new Vacante();
		vac3.setNombre("Actor");
		vac3.setDescripcion("Ser hermoso y sensual");
		vac3.setSalario(43232.32);
		vac3.setFecha(sdf.parse("11/08/2020"));
		vac3.setDestacado(1);
		vac3.setId(3);
		vac3.setEstatus("Eliminada");
		
		Vacante vac4=new Vacante();
		vac4.setNombre("Programador");
		vac4.setDescripcion("Programacion en PHP");
		vac4.setSalario(32.32);
		vac4.setFecha(sdf.parse("11/08/2020"));
		vac4.setImagen("empresa3.jpg");
		vac4.setDestacado(1);
		vac4.setId(4);
		vac4.setEstatus("Aprobada");
		
		lista.add(vac1);
		lista.add(vac2);
		lista.add(vac3);
		lista.add(vac4);
		
		
	}catch(ParseException e){
	System.out.println("Error: "+e.getMessage());
	}
	
	
}
	@Override
	public List<Vacante> obtenerTodo() {
		// TODO Auto-generated method stub
		return lista;
	}
	@Override
	public Vacante obtenerVacantePorId(Integer idVacante) {
		// TODO Auto-generated method stub
		for(Vacante v:lista) {
			if(v.getId()==idVacante) {
				return v;
			}
		}
		return null;
	}
	@Override
	public void guardar(Vacante vacante) {
		// TODO Auto-generated method stub
		lista.add(vacante);
	}
	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void eliminar(Integer idVacante) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Page<Vacante> obtenerTodas(Pageable page) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
