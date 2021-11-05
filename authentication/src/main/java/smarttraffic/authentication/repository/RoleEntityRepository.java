package smarttraffic.authentication.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.authentication.entity.Role;

public interface RoleEntityRepository extends JpaRepository<Role, Integer> {
    Role findByAuthority(String name);
}
