package converter.model;

import java.math.BigDecimal;

import converter.exception.MissingFactorException;

public enum AreaUnit {
  MM2, CM2, M2,
  IN2, FT2, YD2,;

  public BigDecimal factorForUnit() throws MissingFactorException {
    switch (this) {
      case MM2:
      return new BigDecimal(1);
      case CM2:
      return new BigDecimal(100);
      case M2:
      return new BigDecimal(1000000);
      case IN2:
      return new BigDecimal(645.16);
      case FT2:
      return new BigDecimal(92903.04);
      case YD2:
      return new BigDecimal(836127.36);
      default:
      throw new MissingFactorException("AreaUnit is missing a factor for " + this.toString());
    }
  }
}
