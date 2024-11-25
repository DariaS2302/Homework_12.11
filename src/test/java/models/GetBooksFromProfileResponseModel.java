package models;

import lombok.Data;

import java.util.List;

@Data
public class GetBooksFromProfileResponseModel {
    private String userId, username;
    private List<IsbnModel> books;
}
