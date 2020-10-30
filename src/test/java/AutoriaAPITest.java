import io.restassured.RestAssured;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

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
    public Object[] url() throws IOException {
        Properties properties = new Properties();
        String file = properties.getProperty("data.dir") + "/automationpractice-auth-data.csv";
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        List<String> data = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            if (!line.trim().startsWith("#"))
                data.add(line);
        }
        br.close();
        fr.close();

        Object[][] result = new Object[data.size()][3];
        for (int i = 0; i < data.size(); i++) {
            result[i] = data.get(i).split(",");
        }
        return result;
    }
}


