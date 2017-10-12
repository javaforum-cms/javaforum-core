package eu.arcangelovicedomini.javaforum.api.repository;

import eu.arcangelovicedomini.javaforum.api.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

  User findByUsername(String name);
  User findByEmail(String email);
}
