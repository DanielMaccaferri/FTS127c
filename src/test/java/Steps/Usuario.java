package Steps;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.Entao;
import cucumber.api.java.pt.Quando;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Usuario {
    //RestAssured restAssured = new RestAssured();
    RequestSpecification requestSpecification = RestAssured.given();
    //Response response;

    @Dado("^a requisicao contem \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" \"([^\"]*)\" (\\d+)$")
    public void a_requisicao_contem(String id, String username, String firstname, String lastname, String email, String password, String phone, int userStatus) throws Throwable {
        requestSpecification.given() // Dado que
                .contentType("application/json") // Tipo do conteudo REST é sempre assim
                // Existem serviços ASMX e outros, o formato seria text/xml
                .log().all()                     // Gerar um log completo
                // {"id":1

                .body("{\"id\": " + id + "," +
                        "\"username\":" + username + "," +
                        "\"firstname\":" + firstname + "," +
                        "\"lastname\":" + lastname + "," +
                        "\"email\":" + email + "," +
                        "\"password\":" + password + "," +
                        "\"phone\":" + phone + "," +
                        "\"userStatus\":" + userStatus + "}");
    }

    @Quando("^conecto com a uri da PetShop$")
    public void conecto_com_a_uri_da_PetShop() throws Throwable {
        requestSpecification.when()
                .post("https://petstore.swagger.io/v2/user");
    }

    @Entao("^valido (\\d+) \"([^\"]*)\" \"([^\"]*)\"$")
    public void valido(int code , String type, String message) throws Throwable {
        requestSpecification.then()
                .log().all()
                .statusCode(200) // Teste de Interoperabilidade (Conectou)
                .body("code",is(code))
                .body("type",is(type))
                .body("message",is(message));
    }
}

