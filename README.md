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
------------
- **[RESTful Service 기능 확장] Validation**
  - **[중요]** 구현
    - Annotation 추가
      - **[참조]** [User.class](src/main/java/me/study/restful/user/User.java)
      - **[참조]** [UserController.class](src/main/java/me/study/restful/user/UserController.java)
    - Exception Handling - handleMethodArgumentNotValid() Override
      - **[참조]** [CustomizedResponseEntityExceptionHandler.class](src/main/java/me/study/restful/Exception/CustomizedResponseEntityExceptionHandler.java)
------------
- **[RESTful Service 기능 확장] 다국어 처리**
  - 하나의 출력값을 여러가지 언어로 표시해주는 것, 다만 자동 번역이 아닌 해당 값을 미리 저장해둠
  - **[중요]** 구현
    - Bean 등록
      - **[참조]** [RestfulApplication.class](src/main/java/me/study/restful/RestfulApplication.java)
    - Yml 설정 
      - spring.messages.basename: messages
      - **[참조]** [application.yml](src/main/resources/application.yml)
    - messages.properties 저장
      - key, value 저장
      - **[참조]** [messages.properties](src/main/resources/messages.properties)
      - **[참조]** [messages_en.properties](src/main/resources/messages_en.properties)
      - **[참조]** [messages_fr.properties](src/main/resources/messages_fr.properties)
  - **[참조]** [HelloWorldController.class](src/main/java/me/study/restful/helloword/HelloWorldController.java)
  
      