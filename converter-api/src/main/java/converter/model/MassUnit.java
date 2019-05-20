package converter.model;

import java.math.BigDecimal;

import converter.exception.MissingFactorException;

public enum MassUnit {
  MG, G, KG,
  OZ, LB, S;

  public BigDecimal factorForUnit() throws MissingFactorException {
    switch (this) {
      case MG:
      return new BigDecimal(1);
      case G:
      return new BigDecimal(1000);
      case KG:
      return new BigDecimal(1000000);
      case OZ:
      return new BigDecimal(28350);
      case LB:
      return new BigDecimal(453592.37);
      case S:
      return new BigDecimal(6350293.18);
      default:
      throw new MissingFactorException("VolumeUnit is missing a factor for " + this.toString());
    }
  }
}
