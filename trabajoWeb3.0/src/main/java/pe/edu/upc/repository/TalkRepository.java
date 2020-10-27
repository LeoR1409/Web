package pe.edu.upc.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pe.edu.upc.entity.Talk;

@Repository
public interface TalkRepository extends JpaRepository<Talk, Integer>{
	@Query("select count(c.RoomName) from Talk c where c.RoomName =:name")
    public int buscarNombreTalk(@Param("name") String RoomName);
}
