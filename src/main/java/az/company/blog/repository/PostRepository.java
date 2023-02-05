package az.company.blog.repository;

import az.company.blog.entity.Category;
import az.company.blog.entity.Post;
import az.company.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUserAndActive(User user,Integer active);
    List<Post> findByCategoryAndActive(Category category,Integer active);
    Page<Post> findAllByActive(Pageable pageable,Integer active);
    @Query("select p from Post p where p.title like :key")
    List<Post> findByTitleContaining(@Param("key")String title);

    Optional<Post> findByPostIdAndActive(Long postId, Integer active);
}
