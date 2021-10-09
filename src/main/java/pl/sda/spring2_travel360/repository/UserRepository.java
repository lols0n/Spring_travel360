package pl.sda.spring2_travel360.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.sda.spring2_travel360.domain.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByLoginOrEmail(String login, String email);
    Optional<User> findByConfirmationId(String confirmationId);
}
