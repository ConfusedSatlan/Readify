package application.onlinebookstore.repository.user;

import application.onlinebookstore.model.Users;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository extends JpaRepository<Users, Long>,
        JpaSpecificationExecutor<Users> {
    Optional<Users> findByEmail(String email);
}
