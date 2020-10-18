package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.Level_Exercise;
import pe.edu.upc.repository.Level_ExerciseRepository;
import pe.edu.upc.service.ILevel_ExerciseService;

@Service
public class Level_ExerciseServiceImpl implements ILevel_ExerciseService{
	
	@Autowired
	private Level_ExerciseRepository leR;
	
	@Override
	@Transactional
	public Integer insert(Level_Exercise ed) {
		int rpta = leR.buscarNombreNivelEjercicio(ed.getLevel_Name());
		if(rpta == 0) {
			leR.save(ed);
		}
		return rpta;
	}

	@Override
	@Transactional
	public void delete(int idLevel_Exercise) {
		leR.deleteById(idLevel_Exercise);
	}

	@Override
	@Transactional(readOnly=true)
	public List<Level_Exercise> list() {
		return leR.findAll(Sort.by(Sort.Direction.ASC, "name"));
	}
}
