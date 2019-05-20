package converter.model;

import java.math.BigDecimal;

import converter.exception.MissingFactorException;

public enum VolumeUnit {
  ML, L,
  OZ, PT, GAL;

  public BigDecimal factorForUnit() throws MissingFactorException {
    switch (this) {
      case ML:
      return new BigDecimal(1);
      case L:
      return new BigDecimal(1000);
      case OZ:
      return new BigDecimal(29.5735);
      case PT:
      return new BigDecimal(568.261);
      case GAL:
      return new BigDecimal(3785.41);
      default:
      throw new MissingFactorException("VolumeUnit is missing a factor for " + this.toString());
    }
  }
}
