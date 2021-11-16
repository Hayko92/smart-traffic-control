//package repository;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import smarttraffic.detectors_analyzer.entity.Capture;
//import smarttraffic.detectors_analyzer.repository.CaptureRepository;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//import java.time.Instant;
//
//@DataJpaTest
//public class CaptureRepositoryTest {
//
//    private CaptureRepository repository;
//
//    private final Capture capture = new Capture();
//
//    @Test
//    void saveCapture() {
//        String vehiclePlateNumber = "01AM123";
//        Instant instant = Instant.now();
//        capture.setPlateNumber(vehiclePlateNumber);
//        capture.setInstant(instant);
//        capture.setPlace("Best_place");
//        capture.setPhotoUrl("https://PhotoUrl");
//        long countBeforeSave = repository.count();
//        repository.save(capture);
//        assertThat(repository.count()).isEqualTo(countBeforeSave + 1);
//    }
//}
