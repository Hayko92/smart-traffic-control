//package service;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import smarttraffic.detectors_analyzer.entity.Capture;
//import smarttraffic.detectors_analyzer.repository.CaptureRepository;
//import smarttraffic.detectors_analyzer.service.CaptureServiceImpl;
//
//import java.time.Instant;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//public class DetectorServiceImplTest {
//
//    private final CaptureServiceImpl captureServiceImpl = new CaptureServiceImpl();
//
//    @Autowired
//    private CaptureRepository captureRepository;
//
//    @Test
//    void checkGetByNumber() {
//        String vehiclePlateNumber = "01AM123";
//        Capture capture = new Capture();
//        capture.setPlateNumber(vehiclePlateNumber);
//        capture.setPhotoUrl("https://photoUrl");
//        capture.setId(1234567);
//        capture.setInstant(Instant.now());
//        capture.setPlace("Arshakunyac");
//        if ((captureServiceImpl.getByPlateNumber(vehiclePlateNumber)) == null) {
//            captureRepository.save(capture);
//        }
//        assertThat(captureServiceImpl.getByPlateNumber(vehiclePlateNumber)).isNotNull();
//    }
//}