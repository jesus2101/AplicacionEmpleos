package net.bavval.empleosMYSQL.service;

import java.util.List;

import net.bavval.empleosMYSQL.model.Categoria;



public interface IntCategoriasService {
	
	void guardar(Categoria categoria);
	List<Categoria> obtenerTodo();
	Categoria ObtenerPorId(Integer idCategoria);
    void eliminar(Integer idCategoria);
}
