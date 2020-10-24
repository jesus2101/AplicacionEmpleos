package net.bavval.empleosMYSQL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import net.bavval.empleosMYSQL.model.Vacante;



public interface VacantesRepository extends JpaRepository<Vacante, Integer> {

	
	List<Vacante> findByEstatus(String estatus);
	List<Vacante> findByDestacadoAndEstatus(int destacado, String estatus);
	//List<Vacante> findByDestacadoAndEstatusOrderByIdDesc(int destacado, String estatus);
	List<Vacante> findBySalarioBetweenOrderBySalarioDesc(double s1, double s2);
	
	
	
}
