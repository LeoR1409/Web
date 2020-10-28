package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Exercise;

public interface IExercise_Service {
	public Integer insert(Exercise e);
    public void delete(int idExercise);
    List<Exercise> list();
}
