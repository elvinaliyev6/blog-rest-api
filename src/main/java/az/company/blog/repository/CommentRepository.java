package az.company.blog.repository;

import az.company.blog.entity.Comment;
import az.company.blog.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {

    List<Comment> findAllByActiveAndPost(Integer active, Post post);

    Optional<Comment> findByIdAndActive(Long commentId,Integer active);
}
