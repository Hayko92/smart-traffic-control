package smarttraffic.violation_service.util;

public final class PointsCounter {

    private int points = 9;

    private PointsCounter() {
    }

    public int getPoints() {
        points--;
        return points;
    }

}
