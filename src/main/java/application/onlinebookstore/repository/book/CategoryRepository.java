package application.onlinebookstore.repository.book;

import application.onlinebookstore.model.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CategoryRepository extends JpaRepository<Category, Long>,
        JpaSpecificationExecutor<Category> {
    List<Category> findAllByNameContainingIgnoreCase(String name);
}
