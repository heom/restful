package me.study.restful.user;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminUserController {

    private final UserDaoService userDaoService;

    @GetMapping("/v1/users")
    /**
     * @Description [RESTful Service 기능 확장] Response 제어를 위한 Filtering
     * @JsonFilter
     **/
    public MappingJacksonValue retrieveAllUsers(){
        List<User> users = userDaoService.findAll();

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(users);
        mapping.setFilters(filters);

        return mapping;
    }

    /**
     * @Description [RESTful Service 기능 확장] API Version 관리
     *
     **/
//    @GetMapping("/v1/users/{id}") // URI
//    @GetMapping(value="/users/{id}/", params = "version=1") // Request Parameter
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=1") // Request Header
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv1+json") // Request MineType
    /**
     * @Description [RESTful Service 기능 확장] Response 제어를 위한 Filtering
     * @JsonFilter
     **/
    public MappingJacksonValue retrieveUserV1(@PathVariable int id){
        User user = userDaoService.findById(id);

        if(user == null){
            throw new UserNotFoundException(id);
        }

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate","ssn");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfo", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(user);
        mapping.setFilters(filters);

        return mapping;
    }

    /**
     * @Description [RESTful Service 기능 확장] API Version 관리
     *
     **/
//    @GetMapping("/v2/users/{id}") // URI
//    @GetMapping(value="/users/{id}/", params = "version=2")  // Request Parameter
//    @GetMapping(value = "/users/{id}", headers = "X-API-VERSION=2") // Request Header
    @GetMapping(value = "/users/{id}", produces = "application/vnd.company.appv2+json")  // Request MineType
    public MappingJacksonValue retrieveUserV2(@PathVariable int id){
        User user = userDaoService.findById(id);

        if(user == null){
            throw new UserNotFoundException(id);
        }

        // User -> UserV2
        UserV2 userV2 = new UserV2();
        BeanUtils.copyProperties(user, userV2);
        userV2.setGrade("VIP");

        SimpleBeanPropertyFilter filter = SimpleBeanPropertyFilter
                .filterOutAllExcept("id", "name", "joinDate","ssn", "grade");

        FilterProvider filters = new SimpleFilterProvider().addFilter("UserInfoV2", filter);

        MappingJacksonValue mapping = new MappingJacksonValue(userV2);
        mapping.setFilters(filters);

        return mapping;
    }
}
