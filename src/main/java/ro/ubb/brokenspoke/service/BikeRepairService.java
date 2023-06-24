package ro.ubb.brokenspoke.service;

import ro.ubb.brokenspoke.model.BikeRepair;
import ro.ubb.brokenspoke.model.Employee;

import java.util.List;

public interface BikeRepairService {


    List<BikeRepair> getAllBikeRepairs();

    BikeRepair saveBikeRepair(BikeRepair bikeRepair);

    BikeRepair updateBikeRepair(Long id, BikeRepair bikeRepair);

    void deleteBikeRepair(Long id);

    BikeRepair getBikeRepairById(Long id);

    List<BikeRepair> filterBikeRepairsByClientName(String predicate);

    List<BikeRepair> filterBikeRepairsByDueDate(String predicate);

    List<BikeRepair> filterBikeRepairsByStatus(String predicate);

    List<BikeRepair> getAllBikeRepairsSorted(String column, String predicate);

    List<BikeRepair> getAllBikeRepairsByEmployeeId();
}
