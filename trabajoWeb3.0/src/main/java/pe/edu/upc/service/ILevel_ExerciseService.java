package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Level_Exercise;

public interface ILevel_ExerciseService {
	public Integer insert(Level_Exercise le);
    public void delete(int idNivelEjercicio);
    List<Level_Exercise> list();
}
