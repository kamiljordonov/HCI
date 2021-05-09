package org.kamil.schedule.payload;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

    private String fName;
    private String lName;
    private String username;
    private String password;
    private String telephone;

    private String email;

    private String roleString;

}
