package smarttraffic.notifiers.util;

import java.util.Map;

public final class HTMLCreator {
    private HTMLCreator() {
    }

    public static String createSpeedViolationBlank(Map<String, String> info) {
        return String.format("Dear Mr/Mrs %s, You have a you violation\n" +
                        "Violation ID: %s\n" +
                        "Violation type: %s,\n" +
                        "Vehicle platenumber: %s\n" +
                        "Violation place: %s\n" +
                        "Violation time: %s\n" +
                        "Vehicle mark: %s\n" +
                        "Vehicle model: %s\n" +
                        "Vehicle year: %s\n" +
                        "Violation price: %s\n" +
                        "Vehicle owner firstname: %s\n" +
                        "Vehicle owner lastname: %s\n" +
                        "Phone: %s\n" +
                        "Email: %s\n",
                info.get("lastName"),
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
