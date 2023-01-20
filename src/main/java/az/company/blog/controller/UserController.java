package az.company.blog.controller;

import az.company.blog.dto.request.ReqUser;
import az.company.blog.dto.response.BaseResponse;
import az.company.blog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("")
    public BaseResponse getAllUsers(){
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    public BaseResponse getUserById(@PathVariable(value = "id") Long userId){
        return userService.getUserById(userId);
    }
    @PostMapping("")
    public BaseResponse createUser(@RequestBody @Valid ReqUser reqUser){
        return userService.createUser(reqUser);
    }

    @PutMapping("/{id}")
    public BaseResponse updateUser(@PathVariable(value = "id") Long userId,@RequestBody @Valid ReqUser reqUser){
        return userService.updateUser(userId,reqUser);
    }

    @PutMapping("/delete/{id}")
    public BaseResponse deleteUser(@PathVariable("id") Long userId){
        return userService.deleteUser(userId);
    }

}
