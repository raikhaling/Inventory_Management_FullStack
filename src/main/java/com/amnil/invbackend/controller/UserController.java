package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.ApiResponse;
import com.amnil.invbackend.dto.core.UserDto;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.amnil.invbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The type User controller.
 */
@RestController
@RequestMapping("api/v1")
@RequiredArgsConstructor
public class UserController {
    /**
     * userService
     */
    private final UserService userService;


    /**
     * Get all users response entity.
     *
     * @return the response entity
     */
    @GetMapping("/public/users")
    public ResponseEntity<List<UserDto>> getAllUsers(){

        List<UserDto> dtos = userService.getAllUsers();

        dtos.stream().map((ele) -> ele.add(linkTo(methodOn(UserController.class)
                .getUserById(ele.getId())).withSelfRel())).toList();

        if(dtos.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(dtos);
    }

    /**
     * Get user by id response entity.
     *
     * @param id the id
     * @return the response entity
     */

    @GetMapping("/public/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id){
        UserDto dto = userService.getUserById(id);

        //implementation of hateaos
        dto.add(linkTo(methodOn(UserController.class).getAllUsers()).withSelfRel());

        return ResponseEntity.ok(dto);
    }

    /**
     * Delete by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/admin/users/{id}")
    public ResponseEntity<ApiResponse> deleteById(@PathVariable Long id){
        userService.deleteUserById(id);
        return new ResponseEntity<ApiResponse>(
                new ApiResponse("Id :"+id +",is deleted.",true)
                ,HttpStatus.OK);
    }
}
