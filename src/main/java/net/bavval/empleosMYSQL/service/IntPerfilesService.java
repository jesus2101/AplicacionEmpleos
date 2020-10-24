package net.bavval.empleosMYSQL.service;

import java.util.List;

import net.bavval.empleosMYSQL.model.Perfil;



public interface IntPerfilesService {
	List<Perfil> obtenerTodo();
	Perfil obtenerPerfilPorId(Integer idPerfil);
	void guardar(Perfil perfil);
}
