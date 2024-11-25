package tests;

import api.BookStoreApi;
import helpers.extensions.WithLogin;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import pages.ProfilePage;
import static data.AuthorizationData.USER_NAME;

@Tag("API")

public class BookStoreTest extends TestBase {

    @Test
    @WithLogin
    @DisplayName("Проверка успешного удаления книги из корзины")
    void successfulDeleteBookTest() {
        BookStoreApi bookStoreApi = new BookStoreApi();
        bookStoreApi.deleteAllBooksInCart();

        String isbn = bookStoreApi.getRandomIsbn();
        bookStoreApi.addBookToCart(isbn);

        ProfilePage profilePage = new ProfilePage();
        profilePage
                .openPage()
                .checkAuthData(USER_NAME)
                .checkBookInProfile(isbn)
                .deleteBook(isbn)
                .checkResultOnUi(isbn);

        bookStoreApi.checkResultOnApi();
    }

}
