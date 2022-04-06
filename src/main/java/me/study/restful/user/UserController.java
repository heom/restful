package me.study.restful.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = userDaoService.findById(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }
        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(
                                            /**
                                             * @Description [RESTful Service 기능 확장] Validation
                                             **/
                                            @Valid @RequestBody User user){
        User saveUser = userDaoService.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        User user = userDaoService.deleteById(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id
                                           /**
                                            * @Description [RESTful Service 기능 확장] Validation
                                            **/
                                            ,@Valid @RequestBody User user){
        User updateUser = userDaoService.updateById(id, user);
        if(updateUser == null){
            throw new UserNotFoundException(id);
        }
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("")
                .buildAndExpand(updateUser.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }
}
