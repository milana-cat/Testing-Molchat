package Tests;


import org.junit.jupiter.api.*;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import org.json.JSONObject;
import org.apache.commons.io.FileUtils;
import static io.restassured.RestAssured.given;
//import sun.jvm.hotspot.utilities.AddressOps;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APITest extends TestBase2 {

    String body=null;
    String Initial=null;
    {
        try{
           // ReadFilesMojo("/src/test/java/Tests/pet.json")
            Initial=readFile("C:/Users/alexs/AChat/web-chat-main/TestAPI/src/test/java/Tests/TestUser.json");
        }
        catch(Exception e){
        throw new RuntimeException(e);
    }
        JSONObject object = new JSONObject(Initial);
        body=JSONObject.valueToString(object);
    }

    @Test
    @DisplayName("Create user")
    @Order(1)
    @Timeout(7)
    public void test1(){
        given(specification)
                .basePath("/security/register")
                .body(body)
                .post()
                .then()
                //.statusCode(201)
                .statusCode(409)
                //.body("name",equalTo("12345".toString()))
        ;

    }
    @Test
    @DisplayName("Log In")
    @Order(2)
    @Timeout(7)
    public void test2(){
        given(specification)
                .basePath("/security/login")
                .body(body)
                .post()
                .then()
                .statusCode(200);
    }
    /*
    @Test
    @DisplayName("Chat list")
    @Order(4)
    @Timeout(7)
    public void test3(){
        given(specification)
                .basePath("/rooms")
                .get()
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("Log In error")
    @Order(3)
    @Timeout(7)
    public void test3(){
        given(specification)
                .basePath("/security/login")
                .body(body.replace("1234","000"))
                .post()
                .then()
                .statusCode(401);
    }
    @Test
    @DisplayName("Rooms")
    @Order(3)
    @Timeout(7)
    public void test3(){
        given(specification)
                .basePath("/rooms")
                .body(body)
                .post()
                .then()
                .statusCode(404);
    }

    @Test
    @DisplayName("log out")
    @Order(3)
    public void test3(){
        given(specification)
                .basePath("/user/logout")
                .get()
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("Delete user")
    @Order(4)
    public void test4(){
        given(specification)
                .basePath("/user/"+username)
                .delete()
                .then()
                .statusCode(200)
                .body("message",equalTo(username));
        //.body("username",equalTo("Milana2022".toString()));
    }
     */
    public static String readFile(String path) {
        File file = new File(path);
        String content = null;
        try {
            content = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        }catch(IOException e) {
            e.printStackTrace();
        }
        return content;
    }
}
