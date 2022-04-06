package me.study.restful.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;

@Data
@AllArgsConstructor
public class User {
    private Integer id;

    /**
     * @Description [RESTful Service 기능 확장] Validation
     **/
    @NotEmpty(message = "비어 있을 수 없습니다")
    @Size(min=2, message = "2글자 이상 입력해 주세요.")
    private String name;
    @NotNull
    @Past(message = "미래는 입력할 수 없습니다.")
    private Date joinDate;
}
