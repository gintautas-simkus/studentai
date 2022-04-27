package gintautassimkus.studentai;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "studentai")
public class Studentas {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected String vardas;
	protected String pavarde;
	
	public Studentas() {}
	
	public Studentas(String vardas, String pavarde) {
		this.vardas = vardas;
		this.pavarde = pavarde;
	}
	
	public String getVardas() {
		return vardas;
	}
	
	public String getPavarde() {
		return pavarde;
	}
	
	public String getPilnasVardas() {
		return vardas + " " + pavarde;
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
