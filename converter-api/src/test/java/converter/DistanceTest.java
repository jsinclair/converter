package converter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import converter.controller.DistanceController;
import converter.model.DistanceUnit;

public class DistanceTest {

  private DistanceController distanceController = new DistanceController();

  @Test(expected = IllegalArgumentException.class)
  public void distanceInvalidUnit() {
    final DistanceUnit distanceUnit = DistanceUnit.valueOf("lol");
  }

  @Test
  public void distanceCalculation() {
    assertEquals("100", distanceController.distance("cm", "mm", "10").getContent());

    assertEquals("1609344", distanceController.distance("ml", "mm", "1").getContent());
  }

}
