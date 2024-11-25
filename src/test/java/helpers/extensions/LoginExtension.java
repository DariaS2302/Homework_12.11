package helpers.extensions;

import api.AuthorizationApi;
import models.LoginUserResponseModel;
import org.junit.jupiter.api.extension.BeforeEachCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

import static data.AuthorizationData.USER_NAME;
import static data.AuthorizationData.USER_PASSWORD;
import static data.AuthorizationData.USER_ID;
import static data.AuthorizationData.USER_TOKEN;
import static data.AuthorizationData.EXPIRES;
import static data.AuthorizationData.CREATE_DATE;
import static data.AuthorizationData.IS_ACTIVE;

public class LoginExtension implements BeforeEachCallback {

    @Override
    public void beforeEach(ExtensionContext context) {

        USER_NAME = System.getProperty("storeUserName", "login");
        USER_PASSWORD = System.getProperty("storeUserPassword", "password");

        LoginUserResponseModel authResponse = AuthorizationApi.getAuthorizationData(USER_NAME, USER_PASSWORD);

        open("/favicon.ico");

        getWebDriver().manage().addCookie(new Cookie("token", authResponse.getToken()));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.getExpires()));
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.getUserId()));

        USER_ID = authResponse.getUserId();
        USER_TOKEN = authResponse.getToken();
        EXPIRES = authResponse.getExpires();
        CREATE_DATE = authResponse.getCreatedDate();
        IS_ACTIVE = authResponse.getIsActive();
    }
}
