package api;

import models.GetBookListModel;

import static data.AuthorizationData.USER_ID;
import static data.AuthorizationData.USER_TOKEN;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BookStoreSpec.getBookListUserSpec;
import static specs.BookStoreSpec.statusCodeResponse200Spec;

public class ProfileUserApi {

    public static GetBookListModel getListOfBooks() {
        GetBookListModel response = step("Получить список книг", () ->
                given(getBookListUserSpec)
                        .header("Authorization", "Bearer " + USER_TOKEN)
                        .queryParam("UserId", USER_ID)
                        .when()
                        .get("/Account/v1/User/" + USER_ID)
                        .then()
                        .spec(statusCodeResponse200Spec)
                        .extract().as(GetBookListModel.class));

        return response;
    }
}
