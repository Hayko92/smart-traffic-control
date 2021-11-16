package util;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import smarttraffic.cameraimitation.util.NumberExtractor;

public class NumberExtractorTest {

    @Test
    void checkMapToDTO() {
        String text = "35OZ007";
        String result = NumberExtractor.extract(text);
        Assertions.assertThat(result).isNotNull();
        Assertions.assertThat(result).isEqualTo(text);
    }
}
