package com.dius.shopping.domain;

import java.math.BigDecimal;
import java.util.Objects;

public class Product {
  private SKU sku;
  private BigDecimal defaultPrice;

  public Product(SKU sku, BigDecimal defaultPrice) {
    this.sku = sku;
    this.defaultPrice = defaultPrice;
  }

  public SKU getSku() {
    return sku;
  }

  public BigDecimal getDefaultPrice() {
    return defaultPrice;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Product product = (Product) o;
    return sku == product.sku &&
            Objects.equals(defaultPrice, product.defaultPrice);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sku, defaultPrice);
  }

  @Override
  public String toString() {
    return "Item{" +
            "sku=" + sku +
            ", defaultPrice=" + defaultPrice +
            '}';
  }
}
