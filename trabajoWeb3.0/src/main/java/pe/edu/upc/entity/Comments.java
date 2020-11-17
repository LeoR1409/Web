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
@Table(name="comments")
public class Comments implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idComments;
	
	@Column(name="Text", nullable=false, length=60)
	private String Text;
	
	@ManyToOne
    @JoinColumn(name = "idUser")
    private Users users;

	public int getIdComments() {
		return idComments;
	}

	public void setIdComments(int idComments) {
		this.idComments = idComments;
	}

	public String getText() {
		return Text;
	}

	public void setText(String text) {
		Text = text;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	
}
