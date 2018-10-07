package com.dius.shopping.rule;

import com.dius.shopping.constant.Price;
import com.dius.shopping.domain.Product;
import com.dius.shopping.domain.SKU;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

public enum SKUPriceStrategy implements PricingRules {
  IPD_DISCOUNT("Super iPad Discount Rule") {
    /**
     * the brand new Super iPad will have a bulk discounted applied,
     * where the price will drop to $499.99 each, if someone buys more than 4
     */
    @Override
    public BigDecimal applyPriceRule(EnumMap<SKU, List<Product>> items) {
      final List<Product> skuItems = items.get(SKU.IPD);
      if (Objects.nonNull(skuItems) && skuItems.size() > 4) {
        return BigDecimal.valueOf(skuItems.size()).multiply(Price.IPD_SPECIAL);
      } else if (Objects.nonNull(skuItems)){
        return BigDecimal.valueOf(skuItems.size()).multiply(Price.IPD_DEFAULT);
      } else {
        return Price.ZERO;
      }
    }
  },
  MBP_DISCOUNT("MacBook Pro Discount Rule") {
    /**
     * No discounts
     */
    @Override
    public BigDecimal applyPriceRule(EnumMap<SKU , List<Product>> items) {
      final List<Product> mbpItems = items.get(SKU.MBP);
      if (Objects.nonNull(mbpItems)) {
        return BigDecimal.valueOf(mbpItems.size()).multiply(Price.MBP_DEFAULT);
      } else {
        return Price.ZERO;
      }
    }
  },
  ATV_DISCOUNT("Apple TV Discount Rule") {
    /**
     * 3 for 2 deal on Apple TVs. For example, if you buy 3 Apple TVs, you will pay the price of 2 only
     */
    @Override
    public BigDecimal applyPriceRule(EnumMap<SKU , List<Product>> items) {
      return null;
    }
  },
  VGA_DISCOUNT("VGA adapter Discount Rule") {
    /**
     * we will bundle in a free VGA adapter free of charge with every MacBook Pro sold
     */
    @Override
    public BigDecimal applyPriceRule(EnumMap<SKU , List<Product>> items) {
      return null;
    }
  };

  private String description;

  SKUPriceStrategy(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }


}
