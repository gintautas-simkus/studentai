package gintautassimkus.studentai;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface DalykaiRepository extends CrudRepository<Dalykas, Long> {
	  List<Dalykas> findAll();
	  Dalykas findById(long id);
}