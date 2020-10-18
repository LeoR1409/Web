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
@Table(name="level_exercise")

public class Level_Exercise implements Serializable  {
private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idLevel_Exercises;
	
	@Column(name="Level_Name", nullable=false, length=30)
	@Pattern(regexp="(\\d{2}[A-Z])", message="El nombre solo puede tener una letra y un numero")
	private String Level_Name;

	public Level_Exercise() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Level_Exercise(int idLevel_Exercises, String level_Name) {
		super();
		this.idLevel_Exercises = idLevel_Exercises;
		Level_Name = level_Name;
	}

	public int getIdLevel_Exercises() {
		return idLevel_Exercises;
	}

	public void setIdLevel_Exercises(int idLevel_Exercises) {
		this.idLevel_Exercises = idLevel_Exercises;
	}

	public String getLevel_Name() {
		return Level_Name;
	}

	public void setLevel_Name(String level_Name) {
		Level_Name = level_Name;
	}

}
