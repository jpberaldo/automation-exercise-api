package modulos.produto;

import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
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

    @Test
    @DisplayName("Validar que não seja exibida a lista de produtos, fazendo a requisição com o método POST")
    public void testValidarQueNaoPermiteRequisicaoPostParaExibirTodosOsProdutos() {

        baseURI = "https://automationexercise.com/";
        basePath = "api";

        Response listaDeProdutos = given()
                .when()
                .post("/productsList")
                .then()
                .extract().response();

        System.out.println(listaDeProdutos.asString());
        String actual = listaDeProdutos.asString();
        String esperado = "{\"responseCode\": 405, \"message\": \"This request method is not supported.\"}";

        Assertions.assertEquals(esperado, actual);

    }

}
