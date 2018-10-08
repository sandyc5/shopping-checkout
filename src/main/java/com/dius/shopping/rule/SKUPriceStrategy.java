package com.dius.shopping.rule;

import com.dius.shopping.constant.Price;
import com.dius.shopping.domain.Product;
import com.dius.shopping.domain.SKU;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

/**
 * A SKUPriceStrategy will be applied to the Enum SKU type to calculate the price rules for each type
 * It applies the PricingRules to the ordered items for each SKU type
 */
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
        return BigDecimal.ZERO;
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
        return BigDecimal.ZERO;
      }
    }
  },
  ATV_DISCOUNT("Apple TV Discount Rule") {
    /**
     * 3 for 2 deal on Apple TVs. For example, if you buy 3 Apple TVs, you will pay the price of 2 only
     */
    @Override
    public BigDecimal applyPriceRule(EnumMap<SKU , List<Product>> items) {
      final List<Product> atvItems = items.get(SKU.ATV);
      if (Objects.nonNull(atvItems) && atvItems.size() >= 3) {
        int divide = atvItems.size()/3;
        BigDecimal remainder = BigDecimal.valueOf(atvItems.size() % 3);

        return BigDecimal.valueOf(divide * 2).multiply(Price.ATV_DEFAULT).add(remainder.multiply(Price.ATV_DEFAULT));
      } else if (Objects.nonNull(atvItems)) {
        return BigDecimal.valueOf(atvItems.size()).multiply(Price.ATV_DEFAULT);
      }
      return BigDecimal.ZERO;
    }
  },
  VGA_DISCOUNT("VGA adapter Discount Rule") {
    /**
     * we will bundle in a free VGA adapter free of charge with every MacBook Pro sold
     */
    @Override
    public BigDecimal applyPriceRule(EnumMap<SKU , List<Product>> items) {
      final List<Product> vgaItems = items.get(SKU.VGA);
      final List<Product> mbpItems = items.get(SKU.MBP);

      if (Objects.nonNull(vgaItems) && Objects.nonNull(mbpItems)) {
        final int vgaItemsToPrice = vgaItems.size() - mbpItems.size();
        return (vgaItemsToPrice <= 0 ? BigDecimal.ZERO :
                                        BigDecimal.valueOf(vgaItemsToPrice).multiply(Price.VGA_DEFAULT));
      } else if (Objects.isNull(mbpItems) && Objects.nonNull(vgaItems)) {
        return BigDecimal.valueOf(vgaItems.size()).multiply(Price.VGA_DEFAULT);
      }
      return BigDecimal.ZERO;
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
