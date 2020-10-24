package net.bavval.empleosMYSQL.service.db;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.bavval.empleosMYSQL.model.Usuario;
import net.bavval.empleosMYSQL.repository.UsuariosRepository;
import net.bavval.empleosMYSQL.service.IntUsuariosService;

@Service
@Primary
public class UsuariosServiceJpa implements IntUsuariosService{

	
	@Autowired
	private UsuariosRepository usuariosRepo;
	
		
	@Override
	public void guardar(Usuario usuario) {
		usuariosRepo.save(usuario);
		
	}

	@Override
	public void eliminar(Integer idUsuario) {
		usuariosRepo.deleteById(idUsuario);
		
	}

	@Override
	public List<Usuario> buscarTodos() {
		// TODO Auto-generated method stub
		return usuariosRepo.findAll();
	}

}
