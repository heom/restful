package me.study.restful.user;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class UserDaoService {
    private static List<User> users = new ArrayList<>();

    private static int userCount = 3;

    static {
        users.add(new User(1, "Changsun", new Date(), "pass1", "701010-1111111"));
        users.add(new User(2, "Hyuna", new Date(), "pass2", "801111-2222222"));
        users.add(new User(3, "Miok", new Date(), "pass3", "901225-1111111"));
    }

    public List<User> findAll(){
        return users;
    }

    public User save(User user){
        if(user.getId() == null){
            user.setId(++userCount);
        }
        users.add(user);
        return user;
    }

    public User findById(int id){
        for (User user : users) {
            if(user.getId() == id)
                return user;
        }
        return null;
    }

    public User deleteById(int id) {
        Iterator<User> iterator = users.iterator();

        while (iterator.hasNext()){
            User user = iterator.next();

            if(user.getId() == id){
                iterator.remove();
                return user;
            }
        }

        return null;
    }

    public User updateById(int id, User updateUser) {
        for (User user : users) {
            if(user.getId() == id){
                user.setName(updateUser.getName());
                user.setJoinDate(updateUser.getJoinDate());
                return user;
            }
        }
        return null;
    }
}
