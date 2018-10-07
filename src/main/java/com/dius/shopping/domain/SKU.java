package com.dius.shopping.domain;

import com.dius.shopping.rule.SKUPriceStrategy;

public enum SKU  {
  IPD("Super iPad", SKUPriceStrategy.IPD_DISCOUNT) ,
  MBP("MacBook Pro", SKUPriceStrategy.MBP_DISCOUNT),
  ATV("Apple TV", SKUPriceStrategy.ATV_DISCOUNT),
  VGA("VGA adapter", SKUPriceStrategy.VGA_DISCOUNT);

  private String description;
  private SKUPriceStrategy strategy;

  SKU(String description, SKUPriceStrategy strategy) {

    this.description = description;
    this.strategy = strategy;
  }

  public String getDescription() {
    return description;
  }

  public SKUPriceStrategy getPriceStrategy() {
    return strategy;
  }


}

