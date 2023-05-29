package ro.ubb.brokenspoke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.util.List;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Employee {

    @Id
    @GeneratedValue
    private Long employeeId;

    @NonNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$") // letters only
    private String lastName;

    @NonNull
    @NotBlank
    @Pattern(regexp = "^[A-Za-z]+$")  // letters only
    private String firstName;

    private String hireDate;


    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "roleId", referencedColumnName = "roleId")
    @JsonIgnoreProperties("employees")
    private Role role;


    @OneToOne(cascade = CascadeType.ALL, mappedBy = "employee")
//    @JsonIgnoreProperties("employee")
    @JsonIgnoreProperties({"employee"})
    private Login login;

    @OneToMany(mappedBy = "employee")
    @JsonIgnoreProperties("employee")
    private List<BikeRepair> bikeRepairs;
}

