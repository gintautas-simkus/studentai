package gintautassimkus.studentai;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PazymiaiRepository extends CrudRepository<Pazymys, Long> {
	  List<Pazymys> findAll();
	  Pazymys findById(long id);
}