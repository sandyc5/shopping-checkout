package com.dius.shopping.service;

import com.dius.shopping.domain.Product;
import com.dius.shopping.domain.SKU;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutTest {

  @Test
  public void givenProductOrdersWhenGroupBySkuThenCorrectlyGrouped() {

    Product atv = new Product(SKU.ATV, BigDecimal.valueOf(109.50));
    Product vga = new Product(SKU.VGA, BigDecimal.valueOf(30.00));
    Product ipd = new Product(SKU.IPD, BigDecimal.valueOf(549.99));
    //Product mbp = new Product(SKU.MBP, BigDecimal.valueOf(1399.99));


    Checkout checkout = new Checkout();
    checkout.scan(atv);
    checkout.scan(atv);
    checkout.scan(vga);
    checkout.scan(ipd);
    //checkout.scan(mbp);


    EnumMap<SKU, List<Product>> map = checkout.getItemsBySku();
    BigDecimal price =  ipd.getSku().getPriceStrategy().applyPriceRule(map);

    assertEquals(map.size(), 3);
    assertEquals(map.get(SKU.ATV).size(), 2);
    assertEquals(map.get(SKU.VGA).size(), 1);
    assertEquals(map.get(SKU.IPD).size(), 1);
    assertNull(map.get(SKU.MBP));
    assertEquals(ipd.getSku().getPriceStrategy().applyPriceRule(map),(BigDecimal.valueOf(549.99)));





  }
}
