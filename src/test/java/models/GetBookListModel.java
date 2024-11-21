package models;

import lombok.Data;

import java.util.List;

@Data
public class GetBookListModel {
    private String userId,
            username;
    private List<BookModel> books;
}
