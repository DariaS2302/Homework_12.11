package models;

import lombok.Data;

@Data
public class BookModel {

    private String isbn,
            title,
            subTitle,
            author,
            publish_date,
            publisher;

    private int pages;

    private String description,
            website;

}