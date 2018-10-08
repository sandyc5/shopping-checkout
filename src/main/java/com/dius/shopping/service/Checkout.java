package com.dius.shopping.service;

import com.dius.shopping.domain.Product;
import com.dius.shopping.domain.SKU;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.stream.Collectors;

public class Checkout {
  private List<Product> items;

  public Checkout() {
    this.items = new ArrayList<>();
  }

  public void scan(Product item) {
    items.add(item);
  }


  /***
   * Apply the pricing rules to the collection of items ordered at checkout.
   * Return the total for the order.
   ***/
  public BigDecimal total() {
    EnumMap<SKU, List<Product>> orders = getItemsBySku();

    // orders.keySet().forEach(sku -> total.add(sku.getPriceStrategy().applyPriceRule(orders)));
    double total = orders.keySet().stream().mapToDouble(sku-> sku.getPriceStrategy().applyPriceRule(orders).doubleValue()).sum();
//https://stackoverflow.com/questions/22635945/adding-up-bigdecimals-using-streams
    return BigDecimal.valueOf(total);
  }

  public EnumMap<SKU, List<Product>> getItemsBySku() {
    return this.items.stream().collect(Collectors.groupingBy(Product::getSku, () ->
            new EnumMap<>(SKU.class), Collectors.toList()));
  }
}
