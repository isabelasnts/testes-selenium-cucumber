import br.ada.schedule.task.Task;
import br.ada.schedule.user.User;
import com.google.gson.Gson;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class CucumberTests {
    private Gson gson = new Gson();
    private Task task = null;
    private User user = new User();
    private RequestSpecification request = RestAssured.given()
            .baseUri("http://localhost:8080/api/tasks")
            .contentType(ContentType.JSON);
    private Response response;

    @Given("registro nova tarefa")
    public void registroNovaTarefa(String json) {
        response = request.body(json).post();
        response.prettyPrint();
    }

    @Then("pega status")
    public void pegaStatus() {
        String actualStatus = response.getBody().jsonPath().getString("status");
        Assert.assertEquals("OPEN", actualStatus);
    }

    @And("retorna codigo 201")
    public void retornaCodigo201() {
        Assert.assertEquals(201, response.getStatusCode());
    }

    @And("retorna codigo 400")
    public void retornaCodigo400() {
        Assert.assertEquals(400, response.getStatusCode());
    }

    @Then("pega status de erro")
    public void pegaStatusDeErro() {
        String actualStatus = response.getBody().jsonPath().getString("status");
        Assert.assertEquals("400", actualStatus);
    }
    @And("retorna codigo 200")
    public void retornaCodigo() {
    }
    int taskId;
    @When("cadastrado no banco de dados pega id")
    public void cadastradoNoBancoDeDadosPegaId() {
        taskId = response.jsonPath().getInt("id");
        response.prettyPrint();
    }
    @Then("PUT descricao")
    public void putDescricao(String json) {
        response = request.body(json).put(String.valueOf(taskId));
        response.prettyPrint();
    }

}
