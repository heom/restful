package me.study.restful.user;

/**
 * @Description [User service API 구현] Exception Handling - 특정한 Exception 생성
 **/
public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super(String.format("ID[%s] not found", id));
    }
}
