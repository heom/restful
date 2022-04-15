package me.study.restful.user;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
/**
 * @Description [RESTful Service 기능 확장] Response 제어를 위한 Filtering
 * @JsonIgnore, @JsonIgnoreProperties(value = {})
 **/
//@JsonIgnoreProperties(value = {"password", "ssn"})
/**
 * @Description [RESTful Service 기능 확장] Response 제어를 위한 Filtering
 * @JsonFilter
 **/
//@JsonFilter("UserInfo")
/**
 * @Description Spring Boot API 사용] Swagger
 **/
@ApiModel(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
public class User {
    @Id @GeneratedValue
    private Integer id;

    /**
     * @Description [RESTful Service 기능 확장] Validation
     **/
    @NotEmpty(message = "비어 있을 수 없습니다")
    @Size(min=2, message = "2글자 이상 입력해 주세요.")
    @ApiModelProperty(notes = "사용자 이름을 입력해주세요.")
    private String name;
    @NotNull
    @Past(message = "미래는 입력할 수 없습니다.")
    @ApiModelProperty(notes = "사용자 등록일을 입력해주세요.")
    private Date joinDate;

    /**
     * @Description [RESTful Service 기능 확장] Response 제어를 위한 Filtering
     * @JsonIgnore, @JsonIgnoreProperties(value = {})
     **/
//    @JsonIgnore
    @ApiModelProperty(notes = "사용자 등록일를 입력해주세요.")
    private String password;
//    @JsonIgnore
    @ApiModelProperty(notes = "사용자 주민번호를 입력해주세요.")
    private String ssn;
}
