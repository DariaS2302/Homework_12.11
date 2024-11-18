package api;

import models.AddBookRequestModel;
import models.IsbnModel;
import java.util.List;

import static data.AuthorizationData.USER_ID;
import static data.AuthorizationData.USER_TOKEN;
import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.BookStoreSpec.*;

public class BookStoreApi {

    public static void deleteAllBooksInCart() {
        step("Очистить корзину", () -> {
            given(loginRequestSpec)
                    .header("Authorization", "Bearer " + USER_TOKEN)
                    .queryParam("UserId", USER_ID)
                    .when()
                    .delete("/BookStore/v1/Books")
                    .then()
                    .spec(statusCodeResponse204Spec);
        });

    }

    public static void addBookToList(String isbn) {

        AddBookRequestModel request = new AddBookRequestModel();
        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(isbn);
        request.setUserId(USER_ID);
        request.setCollectionOfIsbns(List.of(isbnModel));


        step("Добавить книгу в корзину", () -> {
            given(loginRequestSpec)
                    .header("Authorization", "Bearer " + USER_TOKEN)
                    .body(request)
                    .when()
                    .post("/BookStore/v1/Books")
                    .then()
                    .spec(statusCodeResponse201Spec);
        });

    }
}
