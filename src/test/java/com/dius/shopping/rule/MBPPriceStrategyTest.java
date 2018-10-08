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

public class MBPPriceStrategyTest {
  @Test
  public void given5MBPOrdersWhenApplyPriceRuleThenGetDefaultPriceApplied() {
    Product mbpOrder = new Product(SKU.MBP, Price.MBP_DEFAULT);
    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.MBP, Arrays.asList(mbpOrder, mbpOrder,mbpOrder,mbpOrder,mbpOrder));

    assertEquals(Price.MBP_DEFAULT.multiply(BigDecimal.valueOf(5)), SKU.MBP.getPriceStrategy().applyPriceRule(map));
  }

  @Test
  public void given0MBPOrdersWhenApplyPriceRuleThenNoPiceApplied() {
    Product ipdOrder = new Product(SKU.IPD, Price.IPD_DEFAULT);
    EnumMap<SKU, List<Product>> map = new EnumMap<>(SKU.class);
    map.put(SKU.IPD, Arrays.asList(ipdOrder, ipdOrder,ipdOrder,ipdOrder,ipdOrder));

    assertEquals(BigDecimal.ZERO, SKU.MBP.getPriceStrategy().applyPriceRule(map));
  }
}
