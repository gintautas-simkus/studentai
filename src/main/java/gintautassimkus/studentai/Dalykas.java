package gintautassimkus.studentai;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "dalykai")
public class Dalykas {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected Long id;
	protected String pavadinimas;
	
	public Dalykas() {}
	
	public Dalykas(String pavadinimas) {
		this.pavadinimas = pavadinimas;
	}
	
	public String getPavadinimas() {
		return pavadinimas;
	}
	
	public Long getId() {
		return id;
	}
}
