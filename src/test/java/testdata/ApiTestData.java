package testdata;

import models.*;

import java.util.List;

public class ApiTestData {
    public static final User DEFAULT_USER = new User(0, "FPMI_user_1", "firstName", "lastName", "email@gmail.com", "qwerty", "12345678", 0);
    public static final User DEFAULT_USER_BUILDER = User.builder()
            .id(1)
            .username("FPMI_user_1")
            .firstName("firstName1")
            .lastName("lastName1")
            .email("email2@gmail.com")
            .password("qwerty123")
            .phone("123456789")
            .userStatus(1)
            .build();

    public static final User TEST = User.builder()
            .id(1)
            .username("test")
            .build();

    public static final Items JEANS_ITEM = Items.builder()
            .items(List.of(new Item("0040716474", 1, null)))
            .build();
    public static final BookingDates bookingdates = new BookingDates("2022-01-05", "2022-01-06");
    public static final Booker DEFAULT_BOOKER = new Booker(0, "John", "Doe", 333, true, bookingdates, "Dinner");
}