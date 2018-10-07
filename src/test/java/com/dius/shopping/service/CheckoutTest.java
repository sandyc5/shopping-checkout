package com.dius.shopping.service;

import com.dius.shopping.constant.Price;
import com.dius.shopping.domain.Product;
import com.dius.shopping.domain.SKU;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.EnumMap;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class CheckoutTest {

  @Test
  public void givenProductOrdersWhenGroupBySkuThenCorrectlyGrouped() {

    Product atv = new Product(SKU.ATV, Price.ATV_DEFAULT);
    Product vga = new Product(SKU.VGA, Price.VGA_DEFAULT);
    Product ipd = new Product(SKU.IPD, Price.IPD_DEFAULT);
    Product mbp = new Product(SKU.MBP, Price.MBP_DEFAULT);


    Checkout checkout = new Checkout();
    checkout.scan(atv);
    checkout.scan(atv);
    checkout.scan(vga);
    checkout.scan(ipd);
    //checkout.scan(mbp);


    EnumMap<SKU, List<Product>> map = checkout.getItemsBySku();
    assertEquals(3, map.size());
    assertEquals(2, map.get(SKU.ATV).size());
    assertEquals(1, map.get(SKU.VGA).size());
    assertEquals(1, map.get(SKU.IPD).size());
    assertNull(map.get(SKU.MBP));
  }

}
