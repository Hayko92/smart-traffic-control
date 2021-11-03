package smart_traffic.authentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import smart_traffic.authentication.entity.RoleEntity;

public interface RoleEntityRepository extends JpaRepository<RoleEntity, Integer> {
    RoleEntity findByName(String name);
}
