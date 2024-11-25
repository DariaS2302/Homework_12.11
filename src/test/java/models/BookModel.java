package models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class BookModel {

    private String isbn,
            title,
            subTitle,
            author,
            publish_date,
            publisher;

    @JsonProperty("publish_date")
    private String publishDate;

    private int pages;

}