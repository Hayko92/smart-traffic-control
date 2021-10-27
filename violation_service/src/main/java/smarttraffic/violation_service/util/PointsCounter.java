package smarttraffic.violation_service.util;

public final class PointsCounter {

    private PointsCounter() {
    }

    private int points = 9;

    public int getPoints() {
        points--;
        return points;
    }

}
