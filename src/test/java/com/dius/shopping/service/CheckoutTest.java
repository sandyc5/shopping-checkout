package com.dius.shopping.service;

import com.dius.shopping.constant.Price;
import com.dius.shopping.domain.Product;
import com.dius.shopping.domain.SKU;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.text.NumberFormat;
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
    checkout.scan(atv);
    checkout.scan(atv);
    checkout.scan(vga);
    checkout.scan(ipd);

    EnumMap<SKU, List<Product>> map = checkout.getItemsBySku();
    assertEquals(3, map.size());
    assertEquals(4, map.get(SKU.ATV).size());
    assertEquals(1, map.get(SKU.VGA).size());
    assertEquals(1, map.get(SKU.IPD).size());
    assertNull(map.get(SKU.MBP));
  }

  @Test
  public void givenProductOrdersWhenScanned3ATVAnd1VGAThenGetTotal() {
    /* SKUs Scanned: atv, atv, atv, vga Total expected: $249.00 */

    Product atv = new Product(SKU.ATV, Price.ATV_DEFAULT);
    Product vga = new Product(SKU.VGA, Price.VGA_DEFAULT);

    Checkout checkout = new Checkout();
    checkout.scan(atv);
    checkout.scan(atv);
    checkout.scan(atv);
    checkout.scan(vga);

    BigDecimal total = checkout.total();
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    assertEquals("$249.00", currency.format(total));
  }

  @Test
  public void givenProductOrdersWhenScanned2ATVAnd5IPDThenGetTotal() {
    /* SKUs Scanned: atv, ipd, ipd, atv, ipd, ipd, ipd Total expected: $2718.95 */

    Product atv = new Product(SKU.ATV, Price.ATV_DEFAULT);
    Product ipd = new Product(SKU.IPD, Price.IPD_DEFAULT);

    Checkout checkout = new Checkout();
    checkout.scan(atv);
    checkout.scan(ipd);
    checkout.scan(ipd);
    checkout.scan(atv);
    checkout.scan(ipd);
    checkout.scan(ipd);
    checkout.scan(ipd);

    BigDecimal total = checkout.total();
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    assertEquals("$2,718.95", currency.format(total));
  }

  @Test
  public void givenProductOrdersWhenScanned1MBPAnd1VGAAnd1IPDThenGetTotal() {
    /* SKUs Scanned: mbp, vga, ipd Total expected: $1949.98 */

    Product mbp = new Product(SKU.MBP, Price.MBP_DEFAULT);
    Product vga = new Product(SKU.VGA, Price.VGA_DEFAULT);
    Product ipd = new Product(SKU.IPD, Price.IPD_DEFAULT);

    Checkout checkout = new Checkout();
    checkout.scan(mbp);
    checkout.scan(vga);
    checkout.scan(ipd);


    BigDecimal total = checkout.total();
    NumberFormat currency = NumberFormat.getCurrencyInstance();

    assertEquals("$1,949.98", currency.format(total));
  }

}
