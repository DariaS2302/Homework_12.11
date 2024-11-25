package api;

import io.qameta.allure.Step;
import models.AddBookRequestModel;
import models.GetBooksFromProfileResponseModel;
import models.GetBooksFromStoreResponseModel;
import models.IsbnModel;

import java.util.List;
import java.util.Random;

import static data.AuthorizationData.USER_ID;
import static data.AuthorizationData.USER_TOKEN;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static specs.BookStoreSpec.*;

public class BookStoreApi {

    @Step("Очистить корзину")
    public BookStoreApi deleteAllBooksInCart() {
        given(loginRequestSpec)
                .header("Authorization", "Bearer " + USER_TOKEN)
                .queryParam("UserId", USER_ID)
                .when()
                .delete("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponse204Spec);

        return this;
    }

    @Step("Добавить книгу в корзину")
    public BookStoreApi addBookToCart(String isbn) {

        IsbnModel isbnModel = new IsbnModel();
        isbnModel.setIsbn(isbn);

        AddBookRequestModel request = new AddBookRequestModel();
        request.setUserId(USER_ID);
        request.setCollectionOfIsbns(List.of(isbnModel));

        given(loginRequestSpec)
                .header("Authorization", "Bearer " + USER_TOKEN)
                .body(request)
                .when()
                .post("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponse201Spec);
        return this;

    }

    @Step("Получить список всех книг пользователя")
    public List<IsbnModel> getUserBooks() {

        GetBooksFromProfileResponseModel response =
                given(loginRequestSpec)
                        .when()
                        .header("Authorization", "Bearer " + USER_TOKEN)
                        .get("/Account/v1/User/" + USER_ID)
                        .then()
                        .spec(statusCodeResponse200Spec)
                        .extract().as(GetBooksFromProfileResponseModel.class);

        return response.getBooks();
    }

    @Step("Получить список всех книг магазина")
    public GetBooksFromStoreResponseModel getAllBooks() {

        return given(loginRequestSpec)
                .when()
                .header("Authorization", "Bearer " + USER_TOKEN)
                .get("/BookStore/v1/Books")
                .then()
                .spec(statusCodeResponse200Spec)
                .extract().as(GetBooksFromStoreResponseModel.class);
    }

    @Step("Получить случайный ISBN.")
    public String getRandomIsbn() {

        GetBooksFromStoreResponseModel booksFromStore = getAllBooks();

        Random random = new Random();
        int randomIndex = random.nextInt(booksFromStore.getBooks().size());

        return booksFromStore.getBooks().get(randomIndex).getIsbn();
    }

    @Step("Проверить, что в корзине пусто (API).")
    public void checkResultOnApi() {

        BookStoreApi booksApi = new BookStoreApi();
        List<IsbnModel> books = booksApi.getUserBooks();

        assertTrue(books.isEmpty());
    }

}
