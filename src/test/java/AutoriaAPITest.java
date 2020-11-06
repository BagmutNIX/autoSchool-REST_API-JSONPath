import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static constants.RequestDetails.*;

public class AutoriaAPITest {
    @Test(dataProvider = "url")
    public void autoriaAPITest(String url) {
        RestAssured.given()
                .baseUri(url)
                .header(HEADER_NAME, HEADER_VALUE)
                .when().get()
                .then()
                .statusCode(200)
                .contentType(CONTENT_TYPE)
                .header(CONTENT_ENCODING, HEADER_VALUE_EXPECTED);
    }

    @DataProvider(name = "url")
    public Object[] getDataFromDataProvider() throws IOException {
        List<String> linksList = null;
        try (Stream<String> stream = Files.lines(Paths.get("src/main/resources/autoria_API_links.csv"))) {
            linksList = stream.collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        linksList.forEach(System.out::println);
        return linksList.toArray();
    }
}
