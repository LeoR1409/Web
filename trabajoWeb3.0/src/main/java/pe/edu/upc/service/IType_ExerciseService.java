package pe.edu.upc.service;

import java.util.List;

import pe.edu.upc.entity.Type_Exercise;

public interface IType_ExerciseService {
	public Integer insert(Type_Exercise te);
    public void delete(int idTipoEjercicio);
    List<Type_Exercise> list();
}
