package az.company.blog.dto.request;

import az.company.blog.dto.response.RespCategory;
import az.company.blog.dto.response.RespComment;
import az.company.blog.dto.response.RespUser;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class ReqPost {
    private String title;
    private String content;
    private String imageName;
    private RespCategory category;
    private RespUser user;
    private Set<RespComment> comments = new HashSet<>();
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
