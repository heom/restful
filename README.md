#RESTful Web Service
- RESTful Web Service 복습하기 

## 프로젝트 개발 구성
- Java 8
- Spring Boot(+Maven) 2.6.6
- H2
- SpringDataJPA
- JUnit5

## 추가 정리
- **[User service API 구현] Exception Handling**
  - **[중요]** 구현
    - 특정한 Exception 생성
      - **[참조]** [UserNotFoundException.class](src/main/java/me/study/restful/user/UserNotFoundException.java)
    - 공통 Exception Handling
      - **[참조]** [ExceptionResponse.class](src/main/java/me/study/restful/Exception/ExceptionResponse.java)
      - **[참조]** [CustomizedResponseEntityExceptionHandler.class](src/main/java/me/study/restful/Exception/CustomizedResponseEntityExceptionHandler.java)
  - **[참조]** [UserController.class](src/main/java/me/study/restful/user/UserController.java)
