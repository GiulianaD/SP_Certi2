import lombok.Getter;
import lombok.Setter;

public class invalidBooking {
    @Getter @Setter
    private String firstname;
    @Getter @Setter
    private String lastname;
    @Getter @Setter
    private boolean totalprice;
    @Getter @Setter
    private Integer depositpaid;
    @Getter @Setter
    private BookingDates bookingdates;
    @Getter @Setter
    private String additionalneeds;
}
