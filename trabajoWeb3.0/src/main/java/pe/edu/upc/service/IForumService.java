package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Forum;

public interface IForumService {
	public Integer insert(Forum fo);
    public void delete(int idForums);
    List<Forum> list();
}
