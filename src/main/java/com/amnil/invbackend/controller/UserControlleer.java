package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.ApiResponse;
import com.amnil.invbackend.dto.core.UserDto;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.security.CustomUserDetailService;
import com.amnil.invbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserControlleer {
    private final UserService userService;

    //@PreAuthorize("hasRole('ADMIN')")
//    @PostMapping("/admin/users")
//    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
//        LocalDateTime now = LocalDateTime.now();
//        userDto.setDate(now);
//        UserDto userDto1 = userService.createUser(userDto);
//        return ResponseEntity.ok(userDto1);
//
//    }
    @GetMapping("/public/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto> dtos = userService.getAllUsers();
        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(dtos);
    }

    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/public/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto dto = userService.getUserById(id);
        return ResponseEntity.ok(dto);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<ApiResponse>(
                new ApiResponse("Id :"+id +",is deleted.",true)
                ,HttpStatus.OK);
    }
}
