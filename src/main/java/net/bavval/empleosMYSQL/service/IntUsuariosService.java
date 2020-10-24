package net.bavval.empleosMYSQL.service;

import java.util.List;

import net.bavval.empleosMYSQL.model.Usuario;

public interface IntUsuariosService {
    
	void guardar(Usuario usuario);
	void eliminar(Integer idUsuario);
	List<Usuario> buscarTodos();
}
