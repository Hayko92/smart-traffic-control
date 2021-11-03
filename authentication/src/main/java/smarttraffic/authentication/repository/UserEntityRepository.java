package smart_traffic.authentication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smart_traffic.authentication.entity.UserEntity;

public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {


    UserEntity findByLogin(String login);
}
