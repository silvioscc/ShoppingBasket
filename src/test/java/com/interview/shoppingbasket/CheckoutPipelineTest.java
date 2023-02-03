package com.interview.shoppingbasket;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class CheckoutPipelineTest {

    CheckoutPipeline checkoutPipeline;
    PricingService pricingService;

    @Mock
    Basket basket = new Basket();

    @Mock
    CheckoutStep checkoutStep1;

    @Mock
    CheckoutStep checkoutStep2;

    @BeforeEach
    void setup() {
        checkoutPipeline = new CheckoutPipeline();
    }

    @Test
    void returnZeroPaymentForEmptyPipeline() {
        PaymentSummary paymentSummary = checkoutPipeline.checkout(basket);

        assertEquals(paymentSummary.getRetailTotal(), 0.0);
    }

    @Test
    void executeAllPassedCheckoutSteps() {
        // Exercise - implement testing passing through all checkout steps
        basket.add("product1", "myproduct1", 10, 3.99);
        basket.add("product2", "myproduct2", 10, 2.0, new Promotion(PromotionType.TENOFF));
        basket.add("product3", "myproduct3", 5, 3.29, new Promotion(PromotionType.TWOFORONE));
        basket.add("product4", "myproduct4", 10, 5.00, new Promotion(PromotionType.FIFTYOFF));
        basket.add("product2", "myproduct1", 8, 2.0, new Promotion(PromotionType.TENOFF));
        
        basket.consolidateItems();
        List<BasketItem> basketSize = basket.getItems();
        
        assertEquals(basketSize.size(),4);
        assertEquals(basketSize.get(0).getProductCode(), "product1");
        assertEquals(basketSize.get(0).getProductName(), "myproduct1");
        assertEquals(basketSize.get(0).getQuantity(), 10);
        assertEquals(basketSize.get(1).getProductCode(), "product2");
        assertEquals(basketSize.get(1).getProductName(), "myproduct2");
        assertEquals(basketSize.get(1).getQuantity(), 18);
        assertEquals(basketSize.get(2).getProductCode(), "product3");
        assertEquals(basketSize.get(2).getProductName(), "myproduct3");
        assertEquals(basketSize.get(2).getQuantity(), 5);
        assertEquals(basketSize.get(3).getProductCode(), "product4");
        assertEquals(basketSize.get(3).getProductName(), "myproduct4");
        assertEquals(basketSize.get(3).getQuantity(), 10);
        
        double total=10*3.99+18*2.0-18*2.0*0.1+5*3.29-2*3.29+10*5.00-10*5.0*0.5;
        int temp=(int)(total*100);
        total=(double)temp/100;
        
        CheckoutContext checkoutContext = new CheckoutContext(basket);
        
        RetailPriceCheckoutStep retailPriceCheckoutStep= new RetailPriceCheckoutStep(pricingService);
        
        retailPriceCheckoutStep.execute(checkoutContext);
        
        assertEquals(checkoutContext.getRetailPriceTotal(), total);
    }

}
