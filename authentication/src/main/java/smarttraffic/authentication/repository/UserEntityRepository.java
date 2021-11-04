package smarttraffic.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.authentication.entity.User;

public interface UserEntityRepository extends JpaRepository<User, Integer> {

    User findByLogin(String login);
}
