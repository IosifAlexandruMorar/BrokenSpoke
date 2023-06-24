package ro.ubb.brokenspoke.dto;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UpdatePasswordDTO {
    private String initialPassword;
    private String newPassword;
}
