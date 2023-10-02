package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.core.UserDto;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.UserRepository;
import com.amnil.invbackend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
//    @Override
//    public UserDto createUser(UserDto userDto) {
//        //extract email and check if user already exists
//        String email = userDto.getEmail();
//        //LocalUser existingUser = userRepository.findByEmail(email)
//          //      .orElseThrow(()-> new EntityNotFoundException("User already created"));
////        if(existingUser!= null){
////            throw new EntityNotFoundException("User already exist with email: "+email);
////        }
//        //save a user
//        LocalUser user = modelMapper.map(userDto, LocalUser.class);
//        //userRepository.save(user);
//        userRepository.save(user);
//
//        LocalUser secureUser = new LocalUser();
//        secureUser.setId(user.getId());
//        secureUser.setPhoneNumber(user.getPhoneNumber());
//        secureUser.setEmail(user.getEmail());
//
//        return modelMapper.map(secureUser,UserDto.class);
//    }

    @Override
    public List<UserDto> getAllUsers() {
        List<LocalUser> users;
        if(isAdmin()){
            users = userRepository.findAllUsers();
        }else {
            users = userRepository.findAllUsersExceptAdmin(); // custom query
        }

        return users.stream()
                .map((user)->modelMapper.map(user,UserDto.class)).toList();
    }
    private boolean isAdmin(){ //check if admin
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication.getAuthorities().stream()
                .anyMatch((authority) -> authority.getAuthority().equals("ROLE_ADMIN"));
    }

    @Override
    public UserDto getUserById(Long id) {
        LocalUser user = userRepository.getUserById(id);
        if(user == null){
            throw new EntityNotFoundException("User not found with id: "+id);
        }
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Long id) {
        LocalUser user = modelMapper.map(userDto, LocalUser.class);
        user.setId(id);
        userRepository.customSave(user);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        LocalUser user = userRepository.getUserById(id);
        if(user == null){
            throw new EntityNotFoundException("User not found with id: "+id);
        }
        userRepository.deleteById(id);
    }

}
