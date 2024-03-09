package global.techhub.base.repository.user;

import org.springframework.data.jpa.repository.JpaRepository;
import global.techhub.base.entity.user.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);
}
