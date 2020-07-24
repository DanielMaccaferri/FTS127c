import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class Servico { //Boa noite

    public String lerJson(String caminhoJson) throws IOException {  // serve para carregar qualquer json
        return new String(Files.readAllBytes(Paths.get(caminhoJson)));
    }
    // boa noite oi
    @Test
    public void tc_01_incluir_usuario() throws IOException {
        String resultado = "119751010";

        String jsonBody = lerJson("src/test/resources/usuario.json");

        given()  //Ddao que
                   .contentType("application/json") // Tipo do conteúdo  REST é sempre assim
                // Existem serviços ASMX , o formato seria text/xml
                   .log().all()                       // informações do Json // corpo da mensagem
                // {"id":1 //gerar um log completo // isso é do restAssured é um postman sem interface da para colocar na esteira //dado que eu tenha as informações do json quando entro nesse endereço

                   .body(jsonBody)
        .when()
                     .post("https://petstore.swagger.io/v2/user")


        .then()
                .log().all()
                .statusCode(200)// teste de interoperabilidade (Conectou)
                .body("code", is(200) )  //resposta importa
                .body("type", is("unknown"))
                .body("message", is(resultado))
        ;

    }

    @Test
    public void tc_02_consultar_usuario() throws IOException {

        String resultado = "119751010";

        //String jsonBody = lerJson("src/test/resources/usuario.json");

        given()  //Ddao que request
                .contentType("application/json") // Tipo do conteúdo  REST é sempre assim
                // Existem serviços ASMX , o formato seria text/xml
                .log().all()    //MOSTRA TUDO QUE VAI TRANSMITIR                   // informações do Json // corpo da mensagem
                // {"id":1 //gerar um log completo // isso é do restAssured é um postman sem interface da para colocar na esteira //dado que eu tenha as informações do json quando entro nesse endereço
               // .body(jsonBody) // Lê o arquivo json

        .when()
                     .get("https://petstore.swagger.io/v2/user/AnaClaraDM")

        .then() //response
                .log().all()
                .statusCode(200)// teste de interoperabilidade (Conectou)
                //.body("code", is(200) )  //resposta importa
                //.body("type", is("unknown"))
                //.body("message", is(resultado))
        ;

    }
    @Test // teste
    public void tc_03_alterar_usuario() throws IOException {
        String resultado = "119751010";

        String jsonBody = lerJson("src/test/resources/usuario2.json");// alterou para usuario 2

        given()  //Ddao que
                .contentType("application/json") // Tipo do conteúdo  REST é sempre assim
                // Existem serviços ASMX , o formato seria text/xml
                .log().all()                       // informações do Json // corpo da mensagem
                // {"id":1 //gerar um log completo // isso é do restAssured é um postman sem interface da para colocar na esteira //dado que eu tenha as informações do json quando entro nesse endereço

                .body(jsonBody)
                .when()
                .put("https://petstore.swagger.io/v2/user/AnaClaraDM") // agora é put


                .then()
                .log().all()
                .statusCode(200)// teste de interoperabilidade (Conectou)
                .body("code", is(200) )  //resposta importa
                .body("type", is("unknown"))
                .body("message", is(resultado))
        ;

    }
    @Test
    public void tc_04_deletar_usuario() throws IOException {

        String resultado = "119751010";

        //String jsonBody = lerJson("src/test/resources/usuario.json");

        given()  //Ddao que request
                .contentType("application/json") // Tipo do conteúdo  REST é sempre assim
                // Existem serviços ASMX , o formato seria text/xml
                .log().all()    //MOSTRA TUDO QUE VAI TRANSMITIR                   // informações do Json // corpo da mensagem
                // {"id":1 //gerar um log completo // isso é do restAssured é um postman sem interface da para colocar na esteira //dado que eu tenha as informações do json quando entro nesse endereço
                // .body(jsonBody) // Lê o arquivo json

                .when()
                .delete("https://petstore.swagger.io/v2/user/AnaClaraDM")

                .then() //response
                .log().all()
                .statusCode(404)// teste de interoperabilidade (Conectou)
        //.body("code", is(200) )  //resposta importa
        //.body("type", is("unknown"))
        //.body("message", is(resultado))
        ;

    }

}
