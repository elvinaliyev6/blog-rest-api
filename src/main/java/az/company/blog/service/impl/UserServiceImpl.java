package az.company.blog.service.impl;

import az.company.blog.dto.request.ReqUser;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.dto.response.RespStatus;
import az.company.blog.dto.response.RespUser;
import az.company.blog.entity.User;
import az.company.blog.enums.EnumAvailableStatus;
import az.company.blog.enums.ErrorCodeEnum;
import az.company.blog.exception.BaseException;
import az.company.blog.repository.UserRepository;
import az.company.blog.service.UserService;
import az.company.blog.util.DTOConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final DTOConverter converter;
    private final UserRepository userRepository;

    @Override
    public BaseResponse createUser(ReqUser reqUser) {
        String email = reqUser.getEmail();

        if (userRepository.findByEmailAndActive(email, EnumAvailableStatus.ACTIVE.getValue()).isPresent()) {
            throw new BaseException(ErrorCodeEnum.USERNAME_ALREADY_EXISTS);
        }

        User user = converter.userDTOToUser(reqUser);
        User savedUser = userRepository.save(user);
        RespUser respUser = converter.userToUserDTO(savedUser);
        return BaseResponse.builder()
                .data(respUser)
                .status(RespStatus.getSuccessStatus()).build();
    }

    @Override
    public BaseResponse deleteUser(Long userId) {
        User user = userRepository
                .findByIdAndActive(userId, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.USER_NOT_FOUND));

        user.setActive(EnumAvailableStatus.DEACTIVE.getValue());
        User updatedUser = userRepository.save(user);
        RespUser respUser = converter.userToUserDTO(updatedUser);
        return BaseResponse.builder()
                .data(respUser)
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse updateUser(Long userId, ReqUser reqUser) {
        User user = userRepository
                .findByIdAndActive(userId, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.USER_NOT_FOUND));

        String email = reqUser.getEmail();

        if (userRepository.findByEmailAndActive(email, EnumAvailableStatus.ACTIVE.getValue()).isPresent()) {
            throw new BaseException(ErrorCodeEnum.USERNAME_ALREADY_EXISTS);
        }

        user.setName(reqUser.getName());
        user.setEmail(reqUser.getEmail());
        user.setPassword(reqUser.getPassword());
        User updatedUser = userRepository.save(user);
        RespUser respUser = converter.userToUserDTO(updatedUser);
        return BaseResponse.builder()
                .data(respUser)
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse getUserById(Long userId) {
        User user = userRepository
                .findByIdAndActive(userId, EnumAvailableStatus.ACTIVE.getValue())
                .orElseThrow(() -> new BaseException(ErrorCodeEnum.USER_NOT_FOUND));
        RespUser respUser = converter.userToUserDTO(user);
        return BaseResponse.builder()
                .data(respUser)
                .status(RespStatus.getSuccessStatus())
                .build();
    }

    @Override
    public BaseResponse getAllUsers() {
        List<RespUser> users = userRepository.findAllByActive(EnumAvailableStatus.ACTIVE.getValue())
                .stream().map(converter::userToUserDTO)
                .collect(Collectors.toList());

        if (users.isEmpty() || users == null)
            throw new BaseException(ErrorCodeEnum.EMPTY);
        return BaseResponse.builder()
                .data(users)
                .status(RespStatus.getSuccessStatus())
                .build();
    }
}
