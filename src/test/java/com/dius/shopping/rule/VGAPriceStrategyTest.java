package com.dius.shopping.rule;

import com.dius.shopping.constant.Price;
import com.dius.shopping.domain.Product;
import com.dius.shopping.domain.SKU;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class VGAPriceStrategyTest {
  @Test
  public void given5MBPOrders5VGAOrdersAndWhenApplyPriceRuleThenNoPiceApplied() {
    Product mbpOrder = new Product(SKU.MBP, Price.MBP_DEFAULT);
    Product vgaOrder = new Product(SKU.VGA, Price.VGA_DEFAULT);
    List<Product> mbpOrders = Arrays.asList(mbpOrder, mbpOrder,mbpOrder,mbpOrder,mbpOrder);
    List<Product> vgaOrders = Arrays.asList(vgaOrder, vgaOrder,vgaOrder,vgaOrder,vgaOrder);

    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.MBP, mbpOrders);
    map.put(SKU.VGA, vgaOrders);

    assertEquals(5, mbpOrders.size());
    assertEquals(5, vgaOrders.size());
    assertEquals(BigDecimal.ZERO, SKU.VGA.getPriceStrategy().applyPriceRule(map));
  }

  @Test
  public void given2MBPOrders5VGAOrdersAndWhenApplyPriceRuleThenDefaultPriceApplied() {
    Product mbpOrder = new Product(SKU.MBP, Price.MBP_DEFAULT);
    Product vgaOrder = new Product(SKU.VGA, Price.VGA_DEFAULT);
    List<Product> mbpOrders = Arrays.asList(mbpOrder, mbpOrder);
    List<Product> vgaOrders = Arrays.asList(vgaOrder, vgaOrder,vgaOrder,vgaOrder,vgaOrder);

    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.MBP, mbpOrders);
    map.put(SKU.VGA, vgaOrders);

    BigDecimal vgaItemsToPrice = BigDecimal.valueOf(vgaOrders.size() - mbpOrders.size());
    assertEquals(2, mbpOrders.size());
    assertEquals(5, vgaOrders.size());

    assertEquals(BigDecimal.valueOf(3), vgaItemsToPrice);
    assertEquals(Price.VGA_DEFAULT.multiply(vgaItemsToPrice), SKU.VGA.getPriceStrategy().applyPriceRule(map));
  }

  @Test
  public void given0MBPOrders2VGAOrdersAndWhenApplyPriceRuleThenDefaultPriceApplied() {
    Product vgaOrder = new Product(SKU.VGA, Price.VGA_DEFAULT);
    List<Product> vgaOrders = Arrays.asList(vgaOrder, vgaOrder);

    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.VGA, vgaOrders);

    BigDecimal vgaItemsToPrice = BigDecimal.valueOf(vgaOrders.size());

    assertEquals(BigDecimal.valueOf(2), vgaItemsToPrice);
    assertEquals(Price.VGA_DEFAULT.multiply(vgaItemsToPrice), SKU.VGA.getPriceStrategy().applyPriceRule(map));
  }

  @Test
  public void given0MBPOrders0VGAOrdersAndWhenApplyPriceRuleThenDefaultPriceApplied() {
    Product ipdOrder = new Product(SKU.IPD, Price.IPD_DEFAULT);
    List<Product> ipdOrders = Arrays.asList(ipdOrder, ipdOrder);

    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.IPD, ipdOrders);

    assertEquals(BigDecimal.ZERO, SKU.VGA.getPriceStrategy().applyPriceRule(map));
  }
}
