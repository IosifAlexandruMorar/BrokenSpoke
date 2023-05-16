package ro.ubb.brokenspoke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
//@Data
@Table(name = "role", uniqueConstraints =
        {
                @UniqueConstraint(name = "UniqueRoleName", columnNames = { "roleName",  }),
        })
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Role {

    @Id
    @GeneratedValue
    private Long roleId;

    private String roleName;


    @OneToMany(cascade = CascadeType.ALL, mappedBy = "role")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "role"})
    private List<Employee> employees;

}
