package converter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import converter.controller.TemperatureController;

public class TemperatureTest {

  private TemperatureController temperatureController = new TemperatureController();

  @Test
  public void temperatureUnknownUnit() {
    assertThat(temperatureController.temperature("lol", "10").getContent(), containsString("Unknown"));
  }

  @Test
  public void temperatureCalculation() {
    assertEquals("73.4", temperatureController.temperature("c", "23").getContent());

    assertEquals("-5", temperatureController.temperature("f", "23").getContent());
  }

}
