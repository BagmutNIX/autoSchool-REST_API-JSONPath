import io.restassured.RestAssured;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

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
        List<String> linksList = new ArrayList();
        Reader file = new FileReader("src/main/resources/autoria_API_links.csv");
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.parse(file);
        for (CSVRecord record : records) {
            String columnOne = record.get(0);
            linksList.add(columnOne);
        }
        return linksList.toArray();
    }
}
