package gintautassimkus.studentai;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "pazymiai")
public class Pazymys {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected int pazymys;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentas_dalykas_id")
	protected StudentasDalykas studentasDalykas;
	
	public Pazymys() {}
	
	public Pazymys(StudentasDalykas studentasDalykas, int pazymys) {
		this.studentasDalykas = studentasDalykas;
		this.pazymys = pazymys;
	}
	
	public int getPazymys() {
		return pazymys;
	}
	
	public Long getId() {
		return id;
	}
	
	public StudentasDalykas getStudentasDalykas() {
		return studentasDalykas;
	}
}
