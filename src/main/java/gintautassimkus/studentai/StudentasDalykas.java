package gintautassimkus.studentai;

import java.util.Optional;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "studentai_dalykai")
public class StudentasDalykas {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "studentas_id")
	protected Studentas studentas;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "dalykas_id")
	protected Dalykas dalykas;
	
	public StudentasDalykas() {}
	
	public StudentasDalykas(Studentas studentas, Dalykas dalykas) {
		this.studentas = studentas;
		this.dalykas = dalykas;
	}
	
	public Long getStudentasId() {
		return studentas.getId();
	}
	
	public Long getDalykasId() {
		return dalykas.getId();
	}
	
	public Long getId() {
		return id;
	}
	
	public Studentas getStudentas() {
		return studentas;
	}
	
	public Dalykas getDalykas() {
		return dalykas;
	}
	
	public String getAprasymas() {
		return studentas.getPavardeVardas() + " - " + dalykas.getPavadinimas();
	}
}
