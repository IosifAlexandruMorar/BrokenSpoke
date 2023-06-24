package ro.ubb.brokenspoke.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ro.ubb.brokenspoke.model.BikeRepair;

import java.util.List;

public interface BikeRepairRepository extends JpaRepository<BikeRepair, Long> {

    @Query("SELECT br FROM BikeRepair br WHERE br.clientPhone LIKE CONCAT ('%', :predicate, '%')")
    List<BikeRepair> filterBikeRepairsByClientPhone(String predicate);

    @Query("SELECT br FROM BikeRepair br WHERE br.dueDate LIKE CONCAT ('%', :predicate, '%')")
    List<BikeRepair> filterBikeRepairsByDueDate(String predicate);

    @Query("SELECT br FROM BikeRepair br WHERE br.status LIKE CONCAT ('%', :predicate, '%')")
    List<BikeRepair> filterBikeRepairsByStatus(String predicate);


    List<BikeRepair> findAllByEmployee_EmployeeId(Long employeeId);
}
