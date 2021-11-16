//package Repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import smarttraffic.cameraimitation.entity.Detector;
//import smarttraffic.cameraimitation.repository.DetectorRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@DataJpaTest
//public class DetectorRepositoryTest {
//
//    @Autowired
//    private DetectorRepository detectorRepository;
//
//    private final Detector detector = new Detector();
//
//    @Test
//    void getByPlaceTest() {
//        String vehiclePlateNumber = "Best_place";
//        detector.setPlace(vehiclePlateNumber);
//        long countBeforeSave = detectorRepository.count();
//        detectorRepository.save(detector);
//        assertThat(detectorRepository.count()).isEqualTo(countBeforeSave + 1);
//    }
//
//}
