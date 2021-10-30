package smarttraffic.notifiers.util;

import java.util.Map;

public final class HTMLCreator {
    private HTMLCreator() {
    }

    public static String createSpeedViolationBlank(Map<String, String> info) {
        return String.format("<!DOCTYPE html><html><style>table, th, td { border:1px solid black;} </style><body> <h2>Dear Mr/Ms $s, You have a new speed violation for your vehicle</h2>"
                        +"<table style=\"width:100%\">" +
                        "" +
                        "  <tr>" +
                        "    <td>id</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "  <tr>" +
                        "    <td>type</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>platenumber</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>place</td>" +
                        "    <td>$s</td>" +
                        "    <tr>" +
                        "    <td>time</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>vehicle mark</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>vehicle model</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>vehicle year</td>" +
                        "    <td>$s</td>" +
                        "    <tr>" +
                        "    <td>price</td>" +
                        "    <td><b>$s</b></td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>firstname</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>lastname</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>phone</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "    <tr>" +
                        "    <td>email</td>" +
                        "    <td>$s</td>" +
                        "  </tr>" +
                        "</table>" +
                        "</body>" +
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
    }
}
