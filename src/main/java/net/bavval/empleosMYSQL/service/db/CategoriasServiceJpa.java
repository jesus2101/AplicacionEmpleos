package net.bavval.empleosMYSQL.service.db;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import net.bavval.empleosMYSQL.model.Categoria;
import net.bavval.empleosMYSQL.repository.CategoriasRepository;
import net.bavval.empleosMYSQL.service.IntCategoriasService;

@Service
@Primary
public class CategoriasServiceJpa implements IntCategoriasService{
	
	@Autowired
	private CategoriasRepository categoriasRepo;
	
	

	@Override
	public void guardar(Categoria categoria) {
		// TODO Auto-generated method stub
		categoriasRepo.save(categoria);
	}

	@Override
	public List<Categoria> obtenerTodo() {
		// TODO Auto-generated method stub
		return categoriasRepo.findAll();
		
	}

	@Override
	public Categoria ObtenerPorId(Integer idCategoria) {
		// TODO Auto-generated method stub
		
		Optional<Categoria> optional=categoriasRepo.findById(idCategoria);
		if(optional.isPresent()) {
			Categoria cat=optional.get();
			return cat;
		}
		return null;
		
	}

	@Override
	public void eliminar(Integer idCategoria) {
		categoriasRepo.deleteById(idCategoria);
		
	}
 
}
