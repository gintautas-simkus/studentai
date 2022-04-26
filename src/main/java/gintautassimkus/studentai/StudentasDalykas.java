package gintautassimkus.studentai;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studentai_dalykai")
public class StudentasDalykas {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected Long studentasId;
	protected Long dalykasId;
	
	public StudentasDalykas() {}
	
	public StudentasDalykas(Long studentasId, Long dalykasId) {
		this.studentasId = studentasId;
		this.dalykasId = dalykasId;
	}
	
	public Long getStudentasId() {
		return studentasId;
	}
	
	public Long getDalykasId() {
		return dalykasId;
	}
	
	public Long getId() {
		return id;
	}
}
