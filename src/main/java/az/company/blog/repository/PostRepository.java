package az.company.blog.repository;

import az.company.blog.entity.Category;
import az.company.blog.entity.Post;
import az.company.blog.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Long> {
    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);

    Page<Post> findAllByActive(Pageable pageable,Integer active);

    @Query("select p from Post p where p.title like :key")
    List<Post> findByTitleContaining(@Param("key")String title);
}