package gintautassimkus.studentai;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UsersRepository extends CrudRepository<User, Long> {
	  //List<Dalykas> findAll();
	  User findByEmail(String email);
}