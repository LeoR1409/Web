package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Talk;


public interface ITalkService {
	public Integer insert(Talk tl);
    public void delete(int idTalk);
    List<Talk> list();
}
