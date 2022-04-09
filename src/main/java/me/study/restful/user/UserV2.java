package me.study.restful.user;

import com.fasterxml.jackson.annotation.JsonFilter;
import lombok.Data;

/**
 * @Description [RESTful Service 기능 확장] API Version 관리
 **/
@Data
@JsonFilter("UserInfoV2")
public class UserV2 extends User {
    private String grade;
}
