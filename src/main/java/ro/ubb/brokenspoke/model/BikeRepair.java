package ro.ubb.brokenspoke.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Entity

@Table
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
//@ToString
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BikeRepair {

    @Id
    @GeneratedValue
    private Long repairId;

    private String description;
    private String startDate;
    private String dueDate;

    @Pattern(regexp = "^[A-Za-z]+$")
    private String clientName;

    @Pattern(regexp = "^[0-9]*$")
    private String clientPhone;

    private String status;

    @Min(value=1, message = "repairs are not free")
    private Float price;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.DETACH)
    @JoinColumn(name = "employeeId", referencedColumnName = "employeeId")
    @JsonIgnoreProperties("bikeRepairs")
    private Employee employee;

}
