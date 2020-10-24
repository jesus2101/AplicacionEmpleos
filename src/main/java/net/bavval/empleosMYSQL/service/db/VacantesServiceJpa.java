package net.bavval.empleosMYSQL.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import net.bavval.empleosMYSQL.model.Vacante;
import net.bavval.empleosMYSQL.repository.VacantesRepository;
import net.bavval.empleosMYSQL.service.IntVacantesService;

@Service
@Primary
public class VacantesServiceJpa implements IntVacantesService {
    
	@Autowired
	private VacantesRepository vacantesRepo;
	
	@Override
	public List<Vacante> obtenerTodo() {
		// TODO Auto-generated method stub
		return vacantesRepo.findAll();
		
	}

	@Override
	public Vacante obtenerVacantePorId(Integer idVacante) {
		// TODO Auto-generated method stub
		Optional <Vacante> optional=vacantesRepo.findById(idVacante);
		if(optional.isPresent()) {
			Vacante vac=optional.get();
			return vac;
		}
		return null;
	}

	@Override
	public void guardar(Vacante vacante) {
		// TODO Auto-generated method stub
	vacantesRepo.save(vacante);
		
	}

	@Override
	public List<Vacante> buscarDestacadas() {
		// TODO Auto-generated method stub
		return vacantesRepo.findByDestacadoAndEstatus(1, "Aprobada");
	}

	@Override
	public void eliminar(Integer idVacante) {
		vacantesRepo.deleteById(idVacante);
		
	}

	@Override
	public List<Vacante> buscarByExample(Example<Vacante> example) {
		// TODO Auto-generated method stub
		return vacantesRepo.findAll(example);
	}

	@Override
	public Page<Vacante> obtenerTodas(Pageable page) {
		// TODO Auto-generated method stub
		return vacantesRepo.findAll(page);
	}

	

	

}
