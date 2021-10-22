package smarttraffic.detectors_analyzer.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class NumberExtractor {

    private NumberExtractor() {
    }

    public static String extract(String text) {
        text = text.replace("AM", "");
        text = text.replace("\n", "");
        text = text.replace(" ", "");
        Pattern pattern = Pattern.compile("\\d{2}\\s*[A-Z]{2}\\s*\\d{3}");
        Matcher matcher = pattern.matcher(text);
        String res = null;
        if (matcher.find()) res = matcher.group();
        return res;
    }

}
