package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Editorial;
import pe.edu.upc.repository.EditorialRepository;
import pe.edu.upc.service.IEditorialService;

@Service
public class EditorialServiceImpl implements IEditorialService{

	@Autowired
	private EditorialRepository edR;
	
	@Override
	@Transactional
	public Integer insert(Editorial ed) {
		int rpta = edR.buscarNombreEditorial(ed.getName_Editorial());
		if(rpta == 0) {
			edR.save(ed);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(int idEditorial) {
		edR.deleteById(idEditorial);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Editorial> list() {
		return edR.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
}
