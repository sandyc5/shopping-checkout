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

public class ATVPriceStrategyTest {
  @Test
  public void given2ATVOrderWhenApplyPriceRuleThenGetDefaultPriceApplied() {
    Product atvOrder = new Product(SKU.ATV, Price.ATV_DEFAULT);
    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.ATV, Arrays.asList(atvOrder, atvOrder));

    assertEquals(Price.ATV_DEFAULT.multiply(BigDecimal.valueOf(2)), SKU.ATV.getPriceStrategy().applyPriceRule(map));
  }

  @Test
  public void given7ATVOrdersWhenApplyPriceRuleThenGetDiscountPriceApplied() {
    Product atvOrder = new Product(SKU.ATV, Price.ATV_DEFAULT);
    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.ATV, Arrays.asList(atvOrder, atvOrder, atvOrder, atvOrder, atvOrder, atvOrder, atvOrder));
    BigDecimal atvTotal = Price.ATV_DEFAULT.multiply(BigDecimal.valueOf(4)).add(Price.ATV_DEFAULT);
    assertEquals(atvTotal, SKU.ATV.getPriceStrategy().applyPriceRule(map));
  }

  @Test
  public void given0ATVOrderWhenApplyPriceRuleThenNoPiceApplied() {
    Product mbpOrder = new Product(SKU.MBP, Price.MBP_DEFAULT);
    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.MBP, Arrays.asList(mbpOrder, mbpOrder,mbpOrder,mbpOrder,mbpOrder));

    assertEquals(BigDecimal.ZERO, SKU.ATV.getPriceStrategy().applyPriceRule(map));
  }
}
