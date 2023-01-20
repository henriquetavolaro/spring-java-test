package com.mp.MP.domain.models;
import com.mp.MP.domain.dto.UserJson;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document("User")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    private String id;
    private String name;
    private String email;


    public User(UserJson userJson){
        this.id = userJson.id();
        this.name = userJson.name();
        this.email = userJson.email();
    }

}
