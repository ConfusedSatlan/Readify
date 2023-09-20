package application.onlinebookstore.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@RequiredArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE role SET is_deleted = true WHERE id=?")
@Where(clause = "is_deleted=false")
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private RoleName name;
    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    public enum RoleName {
        ROLE_USER,
        ROLE_ADMIN
    }
}
