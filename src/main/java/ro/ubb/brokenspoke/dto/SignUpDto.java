package ro.ubb.brokenspoke.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class SignUpDto {
    private String userName;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String hireDate;
    private Long idRole;
}
