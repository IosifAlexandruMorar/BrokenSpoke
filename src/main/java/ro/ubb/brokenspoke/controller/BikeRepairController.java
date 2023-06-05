package ro.ubb.brokenspoke.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ro.ubb.brokenspoke.model.BikeRepair;
import ro.ubb.brokenspoke.service.BikeRepairService;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200/")
public class BikeRepairController {

    @Autowired
    private BikeRepairService bikeRepairService;


    @GetMapping("/bikerepair")

    public ResponseEntity<List<BikeRepair>> getAllBikeRepairs() {
        List<BikeRepair> bikeRepairList = bikeRepairService.getAllBikeRepairs();
        return ResponseEntity.ok().body(bikeRepairList);
    }

    @GetMapping("/bikerepair/{id}")
    public ResponseEntity<BikeRepair> getBikeRepairById(@PathVariable(value = "id") Long bikeRepairId) {
        BikeRepair bikeRepair = bikeRepairService.getBikeRepairById(bikeRepairId);
        return ResponseEntity.ok().body(bikeRepair);
    }

    @PostMapping("/bikerepair")
    public BikeRepair createBikeRepair(@Valid @RequestBody BikeRepair bikeRepair) {
        return bikeRepairService.saveBikeRepair(bikeRepair);
    }

    @PutMapping("/bikerepair/id={id}")
//    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"} )
    public BikeRepair updateBikeRepair(@PathVariable(value = "id") Long bikeRepairId,
                                 @Valid @RequestBody BikeRepair bikeRepairDetails) {
        BikeRepair bikeRepair = bikeRepairService.updateBikeRepair(bikeRepairId, bikeRepairDetails);
        return bikeRepair;
    }

    @DeleteMapping("/bikerepair/id={id}")
//    @CrossOrigin(origins = "*", allowedHeaders = "Requestor-Type", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public void deleteBikeRepair(@PathVariable(value = "id") Long bikeRepairId) {
        bikeRepairService.deleteBikeRepair(bikeRepairId);
    }

    //    sorts by any column; order is "asc" or "desc"
    //    /bikerepair/sort/clientName-desc
    @GetMapping("/bikerepair/sort/{column}-{order}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public List<BikeRepair> getAllBikeRepairsSorted(@PathVariable(value = "column") String column,
                                              @PathVariable(value = "order") String order) {
        List<BikeRepair> bikeRepairList = bikeRepairService.getAllBikeRepairsSorted(column, order);
        return bikeRepairList;
    }

    //    /bikerepair/search/clientName=myName
    @GetMapping("/bikerepair/search/clientName={predicate}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public List<BikeRepair> getAllBikeRepairsByClientName(@PathVariable(value = "predicate") String clientName) {
        List<BikeRepair> bikeRepairList = bikeRepairService.filterBikeRepairsByClientName(clientName);
        return bikeRepairList;
    }

    //    /bikerepair/search/dueDate=2023-01-02
    @GetMapping("/bikerepair/search/dueDate={predicate}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public List<BikeRepair> getAllBikeRepairsByDueDate(@PathVariable(value = "predicate") String dueDate) {
        List<BikeRepair> bikeRepairList = bikeRepairService.filterBikeRepairsByDueDate(dueDate);
        return bikeRepairList;
    }

    @GetMapping("/bikerepair/search/status={predicate}")
    @CrossOrigin(origins = "*", exposedHeaders = {"Access-Control-Allow-Origin","Access-Control-Allow-Credentials"})
    public List<BikeRepair> getAllBikeRepairsByStatus(@PathVariable(value = "predicate") String status) {
        List<BikeRepair> bikeRepairList = bikeRepairService.filterBikeRepairsByStatus(status);
        return bikeRepairList;
    }


}
