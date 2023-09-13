package application.onlinebookstore.repository.user;

import application.onlinebookstore.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
    @Query("FROM User u LEFT JOIN u.roles where u.email = :email")
    Optional<User> findByEmail(String email);
}
