package az.company.blog.repository;

import az.company.blog.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    Optional<Category> findByIdAndActive(Long customerId,Integer active);
    List<Category> findAllByActive(Integer active);

}
