package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.DetailsForum;

@Repository
public interface DetailsForumRepository extends JpaRepository<DetailsForum, Integer>{
	@Query("from DetailsForum a where a.question=:parametro")
    public DetailsForum BuscarPregunta(@Param("parametro")String question);
}
