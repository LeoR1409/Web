package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Type_Exercise;
import pe.edu.upc.repository.Type_ExerciseRepository;
import pe.edu.upc.service.IType_ExerciseService;

@Service
public class Type_ExerciseServiceImpl implements IType_ExerciseService{
	
	@Autowired
	private Type_ExerciseRepository leR;
	
	@Override
	@Transactional
	public Integer insert(Type_Exercise ed) {
		int rpta = leR.buscarNombreTipoEjercicio(ed.getType_Name());
		if(rpta == 0) {
			leR.save(ed);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(int idType_Exercise) {
		leR.deleteById(idType_Exercise);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Type_Exercise> list() {
		return leR.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
}
