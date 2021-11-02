package smarttraffic.detectors_analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import smarttraffic.detectors_analyzer.entity.Capture;

public interface CaptureRepository extends JpaRepository<Capture, Integer> {
    Capture findFirstByPlateNumberOrderByIdDesc(String plateNumber);

    Capture findFirstByPlaceOrderByIdDesc(String place);

    Capture findFirstByPlaceAndPlateNumberOrderByIdDesc(String place, String plateNumber);

    Capture getById(int id);
}
