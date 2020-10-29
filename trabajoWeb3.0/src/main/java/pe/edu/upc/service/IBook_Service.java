package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Book;
import pe.edu.upc.entity.Talk;

public interface IBook_Service {
	public Integer insert(Book b);
    public void delete(int idBook);
    List<Book> list();
    List<Book> BuscarNombre(String name);
}
