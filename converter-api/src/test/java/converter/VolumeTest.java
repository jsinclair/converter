package converter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import converter.controller.VolumeController;
import converter.model.VolumeUnit;

public class VolumeTest {

  private VolumeController volumeController = new VolumeController();

  @Test(expected = IllegalArgumentException.class)
  public void volumeInvalidUnit() {
    VolumeUnit columeUnit = VolumeUnit.valueOf("lol");
  }

  @Test
  public void volumeCalculation() {
    assertEquals("10000", volumeController.volume("l", "ml", "10").getContent());

    assertEquals("2.27304", volumeController.volume("pt", "l", "4").getContent().substring(0, 7));
  }

}
