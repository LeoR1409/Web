package pe.edu.upc.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="forum")

public class Forum implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idForums;
	
	@Column(name="Topic", nullable=false, length=30)
	private String Topic;
	
	private int Members;
	
	@Column(name="Status", nullable=false, length=30)
	private String Status;

	public Forum() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Forum(int idForums, String topic, int members, String status) {
		super();
		this.idForums = idForums;
		Topic = topic;
		Members = members;
		Status = status;
	}

	

	public int getIdForums() {
		return idForums;
	}

	public void setIdForums(int idForums) {
		this.idForums = idForums;
	}

	public String getTopic() {
		return Topic;
	}

	public void setTopic(String topic) {
		Topic = topic;
	}

	public int getMembers() {
		return Members;
	}

	public void setMembers(int members) {
		Members = members;
	}

	public String getStatus() {
		return Status;
	}

	public void setStatus(String status) {
		Status = status;
	}
	
}
