package ro.ubb.brokenspoke.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ro.ubb.brokenspoke.model.BikeRepair;
import ro.ubb.brokenspoke.model.Employee;
import ro.ubb.brokenspoke.model.Login;
import ro.ubb.brokenspoke.repository.BikeRepairRepository;
import ro.ubb.brokenspoke.repository.EmployeeRepository;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class BikeRepairServiceImpl implements BikeRepairService{

    @Autowired
    private BikeRepairRepository bikeRepairRepository;
    @Override
    public List<BikeRepair> getAllBikeRepairs() {
        return bikeRepairRepository.findAll();
    }

    @Override
    public BikeRepair saveBikeRepair(BikeRepair bikeRepair) {
        return bikeRepairRepository.save(bikeRepair);

    }

    @Override
    public BikeRepair updateBikeRepair(Long id, BikeRepair bikeRepair) {
        BikeRepair bikeRepairUpdate = bikeRepairRepository.findById(id).orElseThrow();

        bikeRepairUpdate.setClientName(bikeRepair.getClientName());
        bikeRepairUpdate.setClientPhone(bikeRepair.getClientPhone());
        bikeRepairUpdate.setDescription(bikeRepair.getDescription());
        bikeRepairUpdate.setDueDate(bikeRepair.getDueDate());
        bikeRepairUpdate.setPrice(bikeRepair.getPrice());
        bikeRepairUpdate.setStartDate(bikeRepair.getStartDate());
        bikeRepairUpdate.setStatus(bikeRepair.getStatus());
        bikeRepairUpdate.setEmployee(bikeRepair.getEmployee());

        bikeRepairRepository.save(bikeRepairUpdate);

        return bikeRepairUpdate;
    }

    @Override
    public void deleteBikeRepair(Long id) {
        bikeRepairRepository.deleteById(id);

    }

    @Override
    public BikeRepair getBikeRepairById(Long id) {
        return bikeRepairRepository.findById(id).orElseThrow();
    }

    @Override
    public List<BikeRepair> filterBikeRepairsByClientName(String predicate) {
        return bikeRepairRepository.findAll().stream().filter(bikeRepair -> Objects.equals(bikeRepair.getClientName(), predicate)).collect(Collectors.toList());
    }

    @Override
    public List<BikeRepair> filterBikeRepairsByDueDate(String predicate) {
        return bikeRepairRepository.findAll().stream().filter(bikeRepair -> Objects.equals(bikeRepair.getDueDate(), predicate)).collect(Collectors.toList());
    }

    @Override
    public List<BikeRepair> filterBikeRepairsByStatus(String predicate) {
        return bikeRepairRepository.findAll().stream().filter(bikeRepair -> Objects.equals(bikeRepair.getStatus(), predicate)).collect(Collectors.toList());
    }


    @Override
    public List<BikeRepair> getAllBikeRepairsSorted(String column, String order) {
        List<BikeRepair> getAllBikeRepairsSorted = null;
        if (Objects.equals(order, "desc")) {
            getAllBikeRepairsSorted = bikeRepairRepository.findAll(Sort.by(Sort.Direction.DESC, column));
        } else if (Objects.equals(order, "asc")) {
            getAllBikeRepairsSorted = bikeRepairRepository.findAll(Sort.by(Sort.Direction.ASC, column));
        }
        return getAllBikeRepairsSorted;

    }
}
