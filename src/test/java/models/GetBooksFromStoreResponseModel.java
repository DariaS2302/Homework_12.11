package models;

import lombok.Data;

import java.util.List;

@Data
public class GetBooksFromStoreResponseModel {
    private List<BookModel> books;
}
