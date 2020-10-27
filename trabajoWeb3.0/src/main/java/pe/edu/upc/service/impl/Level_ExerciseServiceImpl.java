package pe.edu.upc.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.edu.upc.entity.LevelExercise;
import pe.edu.upc.entity.TypeExercise;
import pe.edu.upc.repository.Level_ExerciseRepository;
import pe.edu.upc.service.ILevel_ExerciseService;

@Service
public class Level_ExerciseServiceImpl implements ILevel_ExerciseService{
	
	@Autowired
	private Level_ExerciseRepository leR;
	
	@Override
	@Transactional
	public Integer insert(LevelExercise ed) {
		int rpta=0;
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
	public List<LevelExercise> list() {
		return leR.findAll();
	}
}
