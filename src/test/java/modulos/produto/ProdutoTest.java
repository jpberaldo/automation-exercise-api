package modulos.produto;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest referentes a pagina de Produto")
public class ProdutoTest {

    @Test
    @DisplayName("Validar que seja exibido a lista com todos os produtos")
    public void testExibirTodosOsProdutos() {

        baseURI = "https://automationexercise.com/";
        basePath = "api";

        Response listaDeProdutos = given()
                .when()
                .get("/productsList")
                .then()
                .statusCode(200)
                .extract().response();

        System.out.println(listaDeProdutos.asString());

        listaDeProdutos.then().assertThat().body("products", not(empty()));

    }


}
