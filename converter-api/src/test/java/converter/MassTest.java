package converter;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.*;

import org.junit.Test;

import converter.controller.MassController;
import converter.model.MassUnit;

public class MassTest {

  private MassController massController = new MassController();

  @Test(expected = IllegalArgumentException.class)
  public void massInvalidUnit() {
    MassUnit massUnit = MassUnit.valueOf("lol");
  }

  @Test
  public void massCalculation() {
    assertEquals("283500", massController.mass("oz", "mg", "10").getContent());

    assertEquals("33.0693", massController.mass("kg", "lb", "15").getContent().substring(0, 7));
  }

}
