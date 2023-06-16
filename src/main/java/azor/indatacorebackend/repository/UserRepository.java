package azor.indatacorebackend.repository;


import azor.indatacorebackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Hamza Ezzakri
 * @CreatedAt 6/25/2022 2:50 PM
 */

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);
    boolean existsByUsername(String username);
}
