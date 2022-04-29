package gintautassimkus.studentai;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
	protected LocalDateTime createdAt;
	protected String komentaras;
	
	public Pazymys() {}
	
	public Pazymys(StudentasDalykas studentasDalykas, int pazymys, String komentaras) {
		this.studentasDalykas = studentasDalykas;
		this.pazymys = pazymys;
		this.komentaras = komentaras;
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
	
	public String getCreatedAt() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		return createdAt.format(formatter);
	}
	
	public String getKomentaras() {
		return komentaras.equals("") ? "-" : komentaras;
	}
}
