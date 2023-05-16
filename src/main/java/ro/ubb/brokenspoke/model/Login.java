package ro.ubb.brokenspoke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity

@Table(name = "login", uniqueConstraints =
        {
                @UniqueConstraint(name = "UniqueEmployeeId", columnNames = { "employeeId" }),
                @UniqueConstraint(name = "UniqueUserName", columnNames = { "userName" })
        })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Login {

    @Id
    @GeneratedValue
    private Long loginId;

    @NonNull
    @NotBlank
    private String password;

    private boolean isApproved;

    @NonNull
    @NotBlank
    private String userName;


    @OneToOne()
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    @JsonIgnoreProperties("login")
    private Employee employee;
}
