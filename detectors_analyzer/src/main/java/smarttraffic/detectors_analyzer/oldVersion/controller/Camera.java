package smarttraffic.detectors_analyzer.oldVersion.controller;

import smarttraffic.detectors_analyzer.oldVersion.entity.Capture;

public interface Camera {
   Capture getCapture(String number);
}
