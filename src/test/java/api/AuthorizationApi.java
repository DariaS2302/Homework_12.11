package api;

import data.LoginData;
import models.LoginUserModel;
import models.LoginUserResponseModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BookStoreSpec.loginRequestSpec;
import static specs.BookStoreSpec.statusCodeResponse200Spec;

public class AuthorizationApi {
    public static LoginUserResponseModel getAuthorizationCookie() {
        LoginUserResponseModel response;
        LoginUserModel request = new LoginUserModel();
        LoginData loginData = new LoginData();
        request.setUserName(loginData.getUserName());
        request.setPassword(loginData.getPassword());

        response = step("Залогинить пользователя", () ->
                given(loginRequestSpec)
                        .body(request)
                        .when()
                        .post("/Account/v1/Login")
                        .then()
                        .spec(statusCodeResponse200Spec)
                        .extract().as(LoginUserResponseModel.class));

        return response;
    }
}