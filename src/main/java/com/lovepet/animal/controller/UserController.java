package com.lovepet.animal.controller;


import com.lovepet.animal.dto.UserLoginRequest;
import com.lovepet.animal.dto.UserRegisterRequest;

import com.lovepet.animal.model.User;
import com.lovepet.animal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@RestController
public class UserController {

    @Autowired
    private UserService userService;




    @PostMapping("/user/register")
    public ResponseEntity<User> register(@RequestBody @Valid UserRegisterRequest userRegisterRequest){
        Integer id = userService.registerUser(userRegisterRequest);

       User user= userService.getUserById(id);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    @PostMapping("/user/login")
    public ResponseEntity<User>  login(@RequestBody @Valid UserLoginRequest userLoginRequest, HttpSession session){
      User user   = userService.login(userLoginRequest);

    if(user==null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        session.setAttribute("userId",user.getId());
        session.setAttribute("email",user.getEmail());
        session.setAttribute("name",user.getName());
        session.setAttribute("tel",user.getTel());
    return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping("/session-userId")
    public  ResponseEntity<User> getsessionusername(HttpSession session){ //@Path用來取得url路徑的值
        User user=new User();
        user.setId((Integer)session.getAttribute("userId"));
        user.setEmail((String)session.getAttribute("email"));
        user.setName((String)session.getAttribute("name"));
        user.setTel((String)session.getAttribute("tel"));
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
    @GetMapping("/sign_out")
    public ResponseEntity signout(HttpSession session){
        session.removeAttribute("userId");
        session.removeAttribute("email");
        session.removeAttribute("name");
        session.removeAttribute("tel");
return ResponseEntity.status(HttpStatus.OK).build();
    }
    @GetMapping("/user/registed")
    public ResponseEntity judgeRegisted(@RequestParam String email){
       Boolean bool=(userService.getUserByEmail(email) !=null ? true:false);
       return  (bool ? ResponseEntity.status(HttpStatus.OK).build():ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }
}
