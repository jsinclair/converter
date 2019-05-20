package converter.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

import converter.model.BasicResult;
import converter.model.MassUnit;
import converter.exception.MissingFactorException;

@RestController
public class MassController {

  @CrossOrigin
  @RequestMapping("/mass")
  public BasicResult mass(@RequestParam(value="from") String from,
  @RequestParam(value="to") String to,
  @RequestParam(value="value") String value) {

    String content = "";

    try {

      final MassUnit fromUnit = MassUnit.valueOf(from.toUpperCase());
      final MassUnit toUnit = MassUnit.valueOf(to.toUpperCase());

      /* Using BigDecimal as there is no limit on user input. */
      final BigDecimal initialValue = new BigDecimal(value);

      final BigDecimal result = (fromUnit.factorForUnit()
        .multiply(initialValue)).divide(toUnit.factorForUnit(), 10, RoundingMode.HALF_UP);

      content = result.stripTrailingZeros().toPlainString();
    } catch (NumberFormatException e) {
      content = String.format("Invalid conversion value: %s", value);
    } catch (IllegalArgumentException e) {
      content = String.format("Invalid mass unit: %s", e.getMessage());
    } catch (MissingFactorException e) {
      content = e.getMessage();
    }

    return new BasicResult(content);
  }
}
