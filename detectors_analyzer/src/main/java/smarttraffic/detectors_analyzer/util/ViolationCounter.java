package smarttraffic.detectors_analyzer.util;

public final class ViolationCounter {

    private ViolationCounter() {
    }

    public static int countSpeedViolationBasePrice(long ms, int m) {
        long seconds =ms/1000;
         long speed =m/seconds;
         double averageSpeedKMH =speed*3.6;
         int overSpeed = ((int) averageSpeedKMH-70);
         int result = 0;
        if (  overSpeed>0 && overSpeed <= 10) result =overSpeed*1000;
        else if (  overSpeed>10 && overSpeed <= 30) result =20000;
        else if (  overSpeed>30 && overSpeed <= 50) result =25000;
        else if (  overSpeed>50 && overSpeed <= 80) result =29000;
        else if (  overSpeed>80 ) result =200000;
        return result;
    }

}
