package net.bavval.empleosMYSQL.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.bavval.empleosMYSQL.model.Perfil;
import net.bavval.empleosMYSQL.repository.PerfilesRepository;
import net.bavval.empleosMYSQL.service.IntPerfilesService;

@Service
@Primary
public class PerfilesServiceJpa implements IntPerfilesService{
    
	@Autowired
	private PerfilesRepository perfilesRepo;
	
	
	@Override
	public List<Perfil> obtenerTodo() {
		// TODO Auto-generated method stub
		return perfilesRepo.findAll();
	}

	@Override
	public Perfil obtenerPerfilPorId(Integer idPerfil) {
		// TODO Auto-generated method stub
		Optional <Perfil> optional=perfilesRepo.findById(idPerfil);
		if(optional.isPresent()) {
			Perfil per=optional.get();
			return per;
		}
		return null;
	}

	@Override
	public void guardar(Perfil perfil) {
		// TODO Auto-generated method stub
		perfilesRepo.save(perfil);
	}

}
