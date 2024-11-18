package tests;

import api.BookStoreApi;
import api.ProfileUserApi;
import helpers.extensions.WithLogin;
import models.GetBookListModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;

import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

@Tag("API")

public class BookStoreTest extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Проверка успешного удаления книги из корзины")
    void successfulDeleteBookTest() {

        step("Удалить все книги из корзины", () ->
                BookStoreApi.deleteAllBooksInCart());

        step("Добавить книгу в корзину", () ->
                BookStoreApi.addBookToList("9781449325862"));

        step("Удалить книгу из корзины", () -> {
            ProfilePage.openPage();
            ProfilePage.deleteOneBook();
        });

        step("Проверить удаление книги через UI", () -> {
            ProfilePage.openPage();
            ProfilePage.checkDeleteBookWithUI();
        });

        step("Получить список книг в корзине через API", () -> {
            GetBookListModel response = ProfileUserApi.getListOfBooks();
            assertThat(response.getBooks()).isNotNull();
        });

        step("Проверить удаление книги через API", () -> {
            GetBookListModel response = ProfileUserApi.getListOfBooks();
            assertThat(response.getBooks()).isEmpty();
        });
    }
    

}
