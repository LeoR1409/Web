package pe.edu.upc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Level_Exercise;

@Repository
public interface Level_ExerciseRepository extends JpaRepository<Level_Exercise, Integer>{
	@Query("select count(c.Level_Name) from Level_Exercise c where c.Level_Name =:name")
    public int buscarNombreNivelEjercicio(@Param("name") String nombreNivelEjercicio);
}
