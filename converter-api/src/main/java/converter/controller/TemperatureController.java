package converter.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import converter.model.BasicResult;

@RestController
public class TemperatureController {

  @CrossOrigin
  @RequestMapping("/temperature")
  public BasicResult temperature(@RequestParam(value="from") String from,
  @RequestParam(value="value") String value) {

    String content = "";

    try {

      /* Using BigDecimal as there is no limit on user input. */
      final BigDecimal initialValue = new BigDecimal(value);

      if (from.toUpperCase().equals("C")) {
        final BigDecimal result = (initialValue.multiply(new BigDecimal(9).divide(new BigDecimal(5)))).add(new BigDecimal(32));
        content = result.stripTrailingZeros().toPlainString();
      } else if (from.toUpperCase().equals("F")) {
        final BigDecimal result = (initialValue.subtract(new BigDecimal(32))
          .multiply(new BigDecimal(5))).divide(new BigDecimal(9), 10, RoundingMode.HALF_DOWN);
        content = result.stripTrailingZeros().toPlainString();
      } else {
        content = "Unknown temperature format: " + from;
      }
    } catch (NumberFormatException e) {
      content = String.format("Invalid conversion value: %s", value);
    }

    return new BasicResult(content);
  }
}
