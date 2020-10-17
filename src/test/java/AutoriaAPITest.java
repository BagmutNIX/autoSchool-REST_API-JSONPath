import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class AutoriaAPITest {

    @Test(dataProvider = "url")
    public void autoriaAPITest(String url) {
        RestAssured.given()
                .baseUri("https://auto.ria.com")
                .basePath(url)
                .header("User-Agent", "Jmeter")
                .when().get()
                .then()
                .statusCode(200)
                .contentType("text/html")
                .header("Content-Encoding", "gzip");
    }

    @DataProvider(name = "url")
    public Object[] url() {
        return new String[]{
                "/auto_mitsubishi_lancer_21990745.html",
                "/auto_toyota_camry_22101863.html",
                "/auto_bmw_520_21996828.html",
                "/auto_nissan_leaf_22104675.html",
                "/auto_nissan_leaf_22104647.html",
                "/auto_mercedes_benz_e_220_21994701.html",
                "/auto_lexus_nx_200_22083607.html"
        };
    }
}


