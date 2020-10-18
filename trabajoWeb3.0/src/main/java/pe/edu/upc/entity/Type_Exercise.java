package pe.edu.upc.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@Entity
@Table(name="type_exercise")


public class Type_Exercise implements Serializable {
	
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idType_Exercises;
	
	@Column(name="Type_Name", nullable=false, length=30)
	@Pattern(regexp="[a-zA-Z]", message="El nombre solo puede tener letras")
	private String Type_Name;

	public Type_Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Type_Exercise(int idType_Exercises, String type_Name) {
		super();
		this.idType_Exercises = idType_Exercises;
		Type_Name = type_Name;
	}

	public int getIdType_Exercises() {
		return idType_Exercises;
	}

	public void setIdType_Exercises(int idType_Exercises) {
		this.idType_Exercises = idType_Exercises;
	}

	public String getType_Name() {
		return Type_Name;
	}

	public void setType_Name(String type_Name) {
		Type_Name = type_Name;
	}
	
	
}
