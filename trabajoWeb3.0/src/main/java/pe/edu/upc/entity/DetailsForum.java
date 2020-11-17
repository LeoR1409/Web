package pe.edu.upc.entity;
import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="details")

public class DetailsForum implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idDetails;
	
	
	@ManyToOne
    @JoinColumn(name = "idComments")
    private Comments comments;

	@ManyToOne
    @JoinColumn(name = "idForums")
    private Forum forum;
	
	@Column(name="Question", nullable=false, length=60)
	private String Question;

	public int getIdDetails() {
		return idDetails;
	}

	public void setIdDetails(int idDetails) {
		this.idDetails = idDetails;
	}

	public Comments getComments() {
		return comments;
	}

	public void setComments(Comments comments) {
		this.comments = comments;
	}

	public Forum getForum() {
		return forum;
	}

	public void setForum(Forum forum) {
		this.forum = forum;
	}

	public String getQuestion() {
		return Question;
	}

	public void setQuestion(String question) {
		Question = question;
	}
	
}
