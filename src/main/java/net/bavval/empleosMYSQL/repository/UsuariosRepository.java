package net.bavval.empleosMYSQL.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bavval.empleosMYSQL.model.Usuario;



public interface UsuariosRepository extends JpaRepository<Usuario, Integer> {

}
