package global.techhub.base.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import global.techhub.base.entity.user.Role;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, String> {

    Optional<Role> findByAuthority(String authority);

    @Query(value = "SELECT * FROM roles WHERE authority = 'USER'", nativeQuery = true)
    Role findUserRole();
}
