package gintautassimkus.studentai;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface StudentaiDalykaiRepository extends CrudRepository<StudentasDalykas, Long> {
	  List<StudentasDalykas> findAll();
	  StudentasDalykas findById(long id);
}