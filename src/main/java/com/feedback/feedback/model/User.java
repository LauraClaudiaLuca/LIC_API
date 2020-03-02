package com.feedback.feedback.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("users")
public class User {
    @Id
    @Indexed
    private String id;

    @Field("name")
    private String name;

    @Field("email")
    @Indexed(unique = true)
    private String email;

    @Field("password")
    private String password;

    @Field("role")
    private String role;

}
