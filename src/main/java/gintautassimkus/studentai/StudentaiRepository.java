package gintautassimkus.studentai;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentaiRepository extends CrudRepository<Studentas, Long> {
	  List<Studentas> findAll();
	  Studentas findById(long id);
}