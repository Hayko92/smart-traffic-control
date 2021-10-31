package smarttraffic.notifiers.util;

import java.util.Map;

public final class HTMLCreator {
    private HTMLCreator() {
    }

    public static String createSpeedViolationBlank(Map<String, String> info) {
        return String.format("Dear Mr/Mrs %s, You have a you violation\n" +
                        " Violation ID: %s\n" +
                        "violation type: %s,\n" +
                        "Vehicle platenumber: %s\n"+
                        "Violation place: %s\n"+
                        "Violation time: %s\n"+
                        "Vehicle mark: %s\n"+
                        "Vehicle model: %s\n"+
                        "Vehicle year: %s\n"+
                        "violation price: %s\n"+
                        "vehicle owner firstname: %s\n"+
                        "vehicle owner lastname: %s\n"+
                        "vehicle phone: %s\n"+
                        "vehicle email: %s\n",
                info.get("surname"),
                info.get("id"),
                info.get("type"),
                info.get("plateNumber"),
                info.get("place"),
                info.get("time"),
                info.get("vehicleMark"),
                info.get("vehicleModel"),
                info.get("vehicleYear"),
                info.get("price"),
                info.get("firstName"),
                info.get("lastName"),
                info.get("phone"),
                info.get("email"));
    }
}
