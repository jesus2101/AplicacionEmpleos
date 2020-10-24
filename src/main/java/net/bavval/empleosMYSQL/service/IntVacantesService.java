package net.bavval.empleosMYSQL.service;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import net.bavval.empleosMYSQL.model.Vacante;



public interface IntVacantesService {
	
	List<Vacante> obtenerTodo();
	Vacante obtenerVacantePorId(Integer idVacante);
	void guardar(Vacante vacante);
	List<Vacante> buscarDestacadas();
	void eliminar(Integer idVacante);
	List<Vacante> buscarByExample(Example<Vacante> example);
	Page<Vacante> obtenerTodas(Pageable page);

}
