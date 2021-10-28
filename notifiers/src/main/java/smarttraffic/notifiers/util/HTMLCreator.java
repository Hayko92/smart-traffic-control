package smarttraffic.notifiers.util;

import java.util.Map;

public final class HTMLCreator {
    private HTMLCreator() {
    }

    public static String createSpeedViolationBlank(Map<String, String> info) {
        String blank = String.format("<!DOCTYPE html>\n" +
                        "<html>\n" +
                        "<style>\n" +
                        "table, th, td {\n" +
                        "  border:1px solid black;\n" +
                        "}\n" +
                        "</style>\n" +
                        "<body>\n" +
                        "\n" +
                        "<h2>Dear Mr/Ms $s, You have a new speed violation for your vehicle</h2>\n" +
                        "\n" +
                        "\n" +
                        "<table style=\"width:100%\">\n" +
                        "\n" +
                        "  <tr>\n" +
                        "    <td>id</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "  <tr>\n" +
                        "    <td>type</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>platenumber</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>place</td>\n" +
                        "    <td>$s</td>\n" +
                        "    <tr>\n" +
                        "    <td>time</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>vehicle mark</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>vehicle model</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>vehicle year</td>\n" +
                        "    <td>$s</td>\n" +
                        "    <tr>\n" +
                        "    <td>price</td>\n" +
                        "    <td><b>$s</b></td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>firstname</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>lastname</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>phone</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "    <tr>\n" +
                        "    <td>email</td>\n" +
                        "    <td>$s</td>\n" +
                        "  </tr>\n" +
                        "</table>\n" +
                        "</body>\n" +
                        "</html>", info.get("surname"),
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
        return blank;
    }
}
