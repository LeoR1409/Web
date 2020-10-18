package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Editorial;

public interface IEditorialService {
	public Integer insert(Editorial le);
    public void delete(int idEditorial);
    List<Editorial> list();
}
