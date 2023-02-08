package az.company.blog.util;

import az.company.blog.dto.request.ReqCategory;
import az.company.blog.dto.request.ReqComment;
import az.company.blog.dto.request.ReqPost;
import az.company.blog.dto.request.ReqUser;
import az.company.blog.dto.response.RespCategory;
import az.company.blog.dto.response.RespComment;
import az.company.blog.dto.response.RespPost;
import az.company.blog.dto.response.RespUser;
import az.company.blog.entity.Category;
import az.company.blog.entity.Comment;
import az.company.blog.entity.Post;
import az.company.blog.entity.User;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DTOConverter {
    private final ModelMapper modelMapper;

    public RespCategory categoryToCategoryDTO(Category category) {
        return modelMapper.map(category, RespCategory.class);
    }

    public Category categoryDTOToCategory(ReqCategory reqCategory) {
        return modelMapper.map(reqCategory, Category.class);
    }


    public User userDTOToUser(ReqUser reqUser) {
        return modelMapper.map(reqUser, User.class);
    }

    public RespUser userToUserDTO(User user) {
        return modelMapper.map(user, RespUser.class);
    }

    public Post postDTOToPost(ReqPost reqPost) {
        return modelMapper.map(reqPost, Post.class);
    }

    public RespPost postToPostDTO(Post post) {
        return modelMapper.map(post, RespPost.class);
    }

    public Comment commentDTOToComment(ReqComment comment) {
        return modelMapper.map(comment, Comment.class);
    }

    public RespComment commentToCommentDTO(Comment comment) {
        return modelMapper.map(comment, RespComment.class);
    }
}
