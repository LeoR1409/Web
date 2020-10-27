package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.LevelExercise;

public interface ILevel_ExerciseService {
	public Integer insert(LevelExercise le);
    public void delete(int idNivelEjercicio);
    List<LevelExercise> list();
}
