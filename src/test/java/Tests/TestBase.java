package Tests;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {
    RequestSpecification specification;
    @BeforeEach
    public void BeforeEach(){
        specification= (RequestSpecification) new RequestSpecBuilder()
                /*
                .setBaseUri("https://petstore.swagger.io/v2")

                 */
                .addHeader("Accept"," application/json, text/plain, */*")
                .addHeader("Content-Type","application/json;;charset=UTF-8")
                .addHeader("Accept-Encoding","gzip,deflate,br")
                .addHeader("Host","localhost:8080")
                //.addHeader("")

                .setBaseUri(("http://localhost"))
                .build();
    }
}
