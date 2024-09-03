import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class createBookingTest {
    String baseURI = "https://restful-booker.herokuapp.com";

    @Test
    public void postBookingTest() throws JsonProcessingException {
        RestAssured.baseURI = baseURI;
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-01-01");
        bookingDates.setCheckout("2024-01-05");
        Booking booking = new Booking();
        booking.setFirstname("Marcos");
        booking.setLastname("Campero");
        booking.setDepositpaid(true);
        booking.setTotalprice(456);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("breakfast");

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when().post("/booking");

        response.then().log().body();
        response.then().assertThat().statusCode(200);
    }

    @Test
    public void postBookingIncomplete() throws JsonProcessingException {
        RestAssured.baseURI = baseURI;
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-01-01");
        bookingDates.setCheckout("2024-01-05");
        Booking booking = new Booking();
        booking.setLastname("Campero");
        booking.setDepositpaid(true);
        booking.setTotalprice(456);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("breakfast");

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when().post("/booking");

        response.then().log().body();
        response.then().assertThat().statusCode(400);
    }

    @Test
    public void postBookingInvalid() throws JsonProcessingException {
        RestAssured.baseURI = baseURI;
        BookingDates bookingDates = new BookingDates();
        bookingDates.setCheckin("2024-01-01");
        bookingDates.setCheckout("2024-01-05");

        invalidBooking booking = new invalidBooking();
        booking.setFirstname("Marcos");
        booking.setLastname("Campero");
        booking.setDepositpaid(123);
        booking.setTotalprice(false);
        booking.setBookingdates(bookingDates);
        booking.setAdditionalneeds("breakfast");

        ObjectMapper mapper = new ObjectMapper();
        String payload = mapper.writeValueAsString(booking);

        Response response = RestAssured.given()
                .contentType("application/json")
                .body(payload)
                .when().post("/booking");

        response.then().log().body();
        response.then().assertThat().statusCode(400);
    }
}
