package models;

import lombok.Data;

import java.util.List;

@Data
public class GetBookListModel {
    String userId,
            username;
    List<BookModel> books;
}
