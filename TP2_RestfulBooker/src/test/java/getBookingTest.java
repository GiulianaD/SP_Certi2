import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

public class getBookingTest {
    String baseURI = "https://restful-booker.herokuapp.com";

    @Test
    public void getBookingTestValid() {
        RestAssured.baseURI = baseURI;
        Response response = RestAssured.given().pathParam("id", "1")
                .when().get("/booking/{id}");

        response.then().assertThat().statusCode(200);
        response.then().assertThat().body("$", Matchers.hasKey("firstname"));
        response.then().assertThat().body("$", Matchers.hasKey("lastname"));
        response.then().assertThat().body("$", Matchers.hasKey("totalprice"));
        response.then().assertThat().body("$", Matchers.hasKey("depositpaid"));
        response.then().assertThat().body("$", Matchers.hasKey("bookingdates"));
        response.then().assertThat().body("bookingdates", Matchers.hasKey("checkin"));
        response.then().assertThat().body("bookingdates", Matchers.hasKey("checkout"));
    }

    @Test
    public void getBookingTestInvalid() {
        RestAssured.baseURI = baseURI;
        Response response = RestAssured.given().pathParam("id", "wrong")
                .when().get("/booking/{id}");
        response.then().assertThat().statusCode(404);
    }
}
