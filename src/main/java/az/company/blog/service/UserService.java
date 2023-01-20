package az.company.blog.service;

import az.company.blog.dto.request.ReqUser;
import az.company.blog.dto.response.BaseResponse;

public interface UserService {
    BaseResponse createUser(ReqUser reqUser);

    BaseResponse deleteUser(Long userId);

    BaseResponse updateUser(Long userId,ReqUser reqUser);

    BaseResponse getUserById(Long userId);

    BaseResponse getAllUsers();
}
