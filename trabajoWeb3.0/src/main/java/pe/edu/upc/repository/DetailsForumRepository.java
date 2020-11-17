package pe.edu.upc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.DetailsForum;

@Repository
public interface DetailsForumRepository extends JpaRepository<DetailsForum, Integer>{

}
