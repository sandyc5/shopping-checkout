package com.dius.shopping.constant;

import java.math.BigDecimal;

public final class Price  {
  public static final BigDecimal ATV_DEFAULT = BigDecimal.valueOf(109.50);
  public static final BigDecimal VGA_DEFAULT = BigDecimal.valueOf(30.00);
  public static final BigDecimal IPD_DEFAULT = BigDecimal.valueOf(549.99);
  public static final BigDecimal IPD_SPECIAL = BigDecimal.valueOf(499.99);
  public static final BigDecimal MBP_DEFAULT = BigDecimal.valueOf(1399.99);

  private Price() {
    throw new AssertionError();
  }
}
