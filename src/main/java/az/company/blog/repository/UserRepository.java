package az.company.blog.repository;

import az.company.blog.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAllByActive(Integer active);

    Optional<User> findByIdAndActive(Long userId,Integer active);

    Optional<User> findByEmailAndActive(String email,Integer active);

    Optional<User> findByUsernameAndActive(String username,Integer active);

}
