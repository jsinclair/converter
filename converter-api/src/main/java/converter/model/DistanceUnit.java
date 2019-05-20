package converter.model;

import java.math.BigDecimal;

import converter.exception.MissingFactorException;

public enum DistanceUnit {
  MM, CM, M, KM,
  IN, FT, YD, ML;

  public BigDecimal factorForUnit() throws MissingFactorException {
    switch (this) {
      case MM:
      return new BigDecimal(1);
      case CM:
      return new BigDecimal(10);
      case M:
      return new BigDecimal(1000);
      case KM:
      return new BigDecimal(1000000);
      case IN:
      return new BigDecimal(25.4);
      case FT:
      return new BigDecimal(304.8);
      case YD:
      return new BigDecimal(914.4);
      case ML:
      return new BigDecimal(1609344);
      default:
      throw new MissingFactorException("DistanceUnit is missing a factor for " + this.toString());
    }
  }
}
