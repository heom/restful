package me.study.restful.user;

import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService userDaoService;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return userDaoService.findAll();
    }

    /**
     * @Description [Spring Boot API 사용] HATEOAS
     **/
    @GetMapping("/users2")
    public ResponseEntity<CollectionModel<EntityModel<User>>> retrieveUserList2() {
        List<EntityModel<User>> result = new ArrayList<>();
        List<User> users = userDaoService.findAll();

        for (User user : users) {
            EntityModel entityModel = EntityModel.of(user);
            entityModel.add(linkTo(methodOn(this.getClass()).retrieveAllUsers()).withSelfRel());

            result.add(entityModel);
        }

        return ResponseEntity.ok(CollectionModel.of(result, linkTo(methodOn(this.getClass()).retrieveAllUsers()).withSelfRel()));
    }

    /**
     * @Description [Spring Boot API 사용] HATEOAS
     **/
    @GetMapping("/users/{id}")
//    public User retrieveUser(@PathVariable int id){
    public ResponseEntity<EntityModel<User>> retrieveUser(@PathVariable int id) {
        User user = userDaoService.findById(id);
        if(user == null){
            throw new UserNotFoundException(id);
        }
//        return user;

        //Hateoas
        EntityModel entityModel = EntityModel.of(user);
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(linkTo.withRel("all-users"));
        return ResponseEntity.ok(entityModel);
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
