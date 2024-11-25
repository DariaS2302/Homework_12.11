package api;

import io.qameta.allure.Step;
import models.LoginUserModel;
import models.LoginUserResponseModel;

import static io.restassured.RestAssured.given;
import static specs.BookStoreSpec.loginRequestSpec;
import static specs.BookStoreSpec.statusCodeResponse200Spec;

public class AuthorizationApi {

    @Step("Залогинить пользователя")

    public static LoginUserResponseModel getAuthorizationData(String userName, String userPassword) {

        LoginUserModel request = new LoginUserModel();
        request.setUserName(userName);
        request.setPassword(userPassword);

        return given()
                .spec(loginRequestSpec)
                .body(request)

                .when()
                .post("/Account/v1/Login")

                .then()
                .spec(statusCodeResponse200Spec)
                .extract().as(LoginUserResponseModel.class);
    }
}