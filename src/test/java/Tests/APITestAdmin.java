package Tests;


import org.apache.commons.io.FileUtils;
import org.json.JSONObject;
import org.junit.jupiter.api.*;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static io.restassured.RestAssured.given;
//import sun.jvm.hotspot.utilities.AddressOps;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class APITestAdmin extends TestBase {

    String body=null;
    String token = null;
    String Initial=null;
    {
        try{
           // ReadFilesMojo("/src/test/java/Tests/pet.json")
            Initial=readFile("C:/Users/alexs/AChat/web-chat-main/TestAPI/Testing-Molchat/src/test/java/Tests/TestAdmin.json");
        }
        catch(Exception e){
        throw new RuntimeException(e);
    }
        JSONObject object = new JSONObject(Initial);
        body=JSONObject.valueToString(object);
    }
/*    @Test
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

 */
    @Test
    @DisplayName("Log In")
    @Order(2)
    @Timeout(7)
    public void test2(){
        token = given(specification)
                .basePath("/security/login")
                .body(body)
                .post()
                .then()
                .statusCode(200)
                .extract()
                .headers()
                .getValue("Authorization").substring(7)

                ;

    }
    @Test
    @DisplayName("Chat Rooms")
    @Order(3)
    @Timeout(7)
    public void test3(){
        given(specification)
                .auth()
                .oauth2(token)
                .basePath("/rooms")
                .body(body)
                .post()
                .then()
                .statusCode(404);
    }
    @Test
    @DisplayName("My Profile")
    @Order(4)
    @Timeout(7)
    public void test4(){
        given(specification)
                .auth()
                .oauth2(token)
                .basePath("/user/me")
                .get()
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("All users")
    @Order(5)
    @Timeout(7)
    public void test5(){
        given(specification)
                .auth()
                .oauth2(token)
                .basePath("/user/all")
                .get()
                .then()
                .statusCode(200);
    }
    @Test
    @DisplayName("Get user by ID")
    @Order(6)
    @Timeout(7)
    public void test6(){
        given(specification)
                .auth()
                .oauth2(token)
                .basePath("/user/id/12")
                .get()
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
