package testdata;

import models.User;

public class ApiTestData {
    public static final User DEFAULT_USER = new User(0, "FPMI_user_1", "firstName", "lastName", "email@gmail.com", "qwerty", "12345678", 0);
    public static final User DEFAULT_USER_BUILDER = User.builder()
            .id(0)
            .username("FPMI_user_1")
            .firstName("firstName1")
            .lastName("lastName1")
            .email("email2@gmail.com")
            .password("qwerty123")
            .phone("123456789")
            .userStatus(0)
            .build();
}