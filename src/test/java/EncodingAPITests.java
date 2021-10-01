import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class EncodingAPITests {

    @Test
    public void encodingAPIJSONTest() {
        String lastYear = given()
                .when()
                .get("https://status.encoding.com/status.php?format=json")
                .then()
                .body("status", equalTo("Ok"))
                .log().body()
                .extract().body().path("incident_count.lastYear").toString();

        Assert.assertTrue(10 > Integer.parseInt(lastYear));

        Integer uptime = given()
                .when()
                .get("https://status.encoding.com/status.php?format=json")
                .then()
                .extract().body().path("uptime");

        Assert.assertTrue(1 < TimeUnit.SECONDS.toDays(uptime));
    }

    @Test
    public void encodingAPIXMLTest() {
        String lastYear = given()
                .when()
                .get("https://status.encoding.com/status.php?format=xml")
                .then()
                .body("responce.status", equalTo("Ok"))
                .log().body()
                .extract().body().path("responce.incident_count.lastYear").toString();

        Assert.assertTrue(10 > Integer.parseInt(lastYear));

        String uptime = given()
                .when()
                .get("https://status.encoding.com/status.php?format=xml")
                .then()
                .extract().body().path("responce.uptime");

        Assert.assertTrue(1 < TimeUnit.SECONDS.toDays(Integer.parseInt(uptime)));
    }
}
