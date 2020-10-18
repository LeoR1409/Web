package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Type_Exercise;

@Repository
public interface Type_ExerciseRepository extends JpaRepository<Type_Exercise, Integer>{
	@Query("select count(c.Type_Name) from Type_Exercise c where c.Type_Name =:name")
    public int buscarNombreTipoEjercicio(@Param("name") String nombreTipoEjercicio);
}
