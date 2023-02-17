package com.lovepet.animal.controller;


import com.lovepet.animal.dto.ForumArticleRequest;
import com.lovepet.animal.dto.UserLoginRequest;
import com.lovepet.animal.dto.UserRegisterRequest;

import com.lovepet.animal.dto.UserUpdateRequest;
import com.lovepet.animal.model.ForumArticle;
import com.lovepet.animal.model.User;
import com.lovepet.animal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Date;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/register")//註冊
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest) {
        System.out.println(userRegisterRequest.getGender());
        Integer id = userService.registerUser(userRegisterRequest);

        User user = userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/user/login")//登入
    public ResponseEntity<User> login(@RequestBody @Valid UserLoginRequest userLoginRequest, HttpSession session) {
        User user = userService.login(userLoginRequest);

        if (user == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        session.setAttribute("userId", user.getId());
        session.setAttribute("email", user.getEmail());
        session.setAttribute("password", user.getPassword());
        session.setAttribute("name", user.getName());
        session.setAttribute("tel", user.getTel());
        session.setAttribute("gender", user.getGender());
        session.setAttribute("createdDate", user.getCreatedDate());
        session.setAttribute("lastModifiedDate", user.getLastModifiedDate());
        session.setAttribute("avatar", user.getAvatar());
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/session-userId")//取得登入session
    public ResponseEntity<User> getUserSession(HttpSession session) { //@Path用來取得url路徑的值
        User user = new User();
        user.setId((Integer) session.getAttribute("userId"));
        user.setPassword((String) session.getAttribute("password"));
        user.setEmail((String) session.getAttribute("email"));
        user.setName((String) session.getAttribute("name"));
        user.setTel((String) session.getAttribute("tel"));
        user.setGender((String) session.getAttribute("gender"));
        user.setCreatedDate((Date) session.getAttribute("createdDate"));
        user.setLastModifiedDate((Date) session.getAttribute("lastModifiedDate"));
        user.setAvatar((String) session.getAttribute("avatar"));

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/user/{userId}")//查詢使用者資訊
    public ResponseEntity<User> getUserInfo(@PathVariable Integer userId) {
        User user = userService.getUserById(userId);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @PutMapping("/user/{userId}")//修改user資料
    public String updateUser(@PathVariable Integer userId,
                                           @RequestBody @Valid UserUpdateRequest userUpdateRequest) {

        //檢查userId 是否存在
        User user = userService.getUserById(userId);

        if (user == null) {//找不到回傳404 NOT_FOUND
            return "會員不存在";
        }

        //修改文章內容
        return userService.updateUser(userId, userUpdateRequest);
    }

    @GetMapping("/sign_out")//登出
    public ResponseEntity signout(HttpSession session) {
        session.removeAttribute("userId");
        session.removeAttribute("email");
        session.removeAttribute("name");
        session.removeAttribute("tel");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @GetMapping("/user/registed")
    public ResponseEntity judgeRegisted(@RequestParam String email) {
        Boolean bool = (userService.getUserByEmail(email) != null ? true : false);
        return (bool ? ResponseEntity.status(HttpStatus.OK).build() : ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
