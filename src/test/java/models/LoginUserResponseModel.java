package models;

import lombok.Data;

@Data
public class LoginUserResponseModel {
    private String userId, username, password, token, expires, created_date;
    private Boolean isActive;
}
