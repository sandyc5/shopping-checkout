package com.dius.shopping.rule;

import com.dius.shopping.domain.Product;
import com.dius.shopping.domain.SKU;

import java.math.BigDecimal;
import java.util.EnumMap;
import java.util.List;

@FunctionalInterface
public interface PricingRules {
   BigDecimal applyPriceRule(EnumMap<SKU, List<Product>> orders);
}
