import io.restassured.response.Response;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LiveEndpointTest {

    private static Response response;

    @AfterEach
    public void setup() {
        System.out.println(response.asString());
    }

    @Test
    public void getLiveCodeTest() {
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.FORMAT);
        response.then().statusCode(200);
    }

    @Test
    public void getCurrenciesResultsTest() {
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.FORMAT);
        response.then().body("success", equalTo(true));
        response.then().body("source", equalTo("USD"));
        response.then().body("quotes", notNullValue());
        response.then().body("quotes.USDAED", greaterThan(0f));
        response.then().body("quotes.USDZWL", greaterThan(0f));
        response.then().body("quotes.USDHUF", greaterThan(0f));

    }

    @Test
    public void timeStampLiveEndpointTest() {
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + Consts.FORMAT);
        String expected = LocalDate.now().minusDays(1).toString();
        Integer actualMs = response.path("timestamp");
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date2 = new Date((long) actualMs * 1000);
        String actual = format.format(date2.getTime());
        Assertions.assertEquals(expected, actual);
    }


    @Test
    public void getLiveEndPointSelectedCurrenciesTest() {
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY + "&currencies=CAD,EUR,NIO,RUB");
        response.then().body("quotes.USDCAD", notNullValue());
        response.then().body("quotes.USDEUR", notNullValue());
        response.then().body("quotes.USDNIO", notNullValue());
        response.then().body("quotes.USDRUB", notNullValue());
        response.then().body("quotes.USDAUD", equalTo(null));
        response.then().body("quotes.USDZWL", equalTo(null));
    }


    @Test
    public void getLiveSpeedTest() {
        response = given().get(Consts.BASE_URL + Consts.LIVE_ENDPOINT + Consts.ACCESS_KEY);
        response.then().time(lessThan(1000L));
    }


}
