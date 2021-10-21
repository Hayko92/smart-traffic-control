package smarttraffic.detectors_analyzer.controller;

import smarttraffic.detectors_analyzer.entity.Capture;

public interface Camera {
   Capture getCapture(String number);
}
