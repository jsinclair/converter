package converter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import converter.controller.AreaController;
import converter.model.AreaUnit;

public class AreaTest {

  private AreaController areaController = new AreaController();

  @Test(expected = IllegalArgumentException.class)
  public void areaInvalidUnit() {
    AreaUnit areaUnit = AreaUnit.valueOf("lol");
  }

  @Test
  public void areaCalculation() {
    assertEquals("1000", areaController.area("cm2", "mm2", "10").getContent());

    assertEquals("64.516", areaController.area("in2", "cm2", "10").getContent());
  }

}
