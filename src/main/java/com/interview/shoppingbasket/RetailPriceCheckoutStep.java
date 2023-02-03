package com.interview.shoppingbasket;

import java.text.DecimalFormat;

public class RetailPriceCheckoutStep implements CheckoutStep {
    private PricingService pricingService;
    private double retailTotal;

    public RetailPriceCheckoutStep(PricingService pricingService) {
        this.pricingService = pricingService;
    }

    @Override
    public void execute(CheckoutContext checkoutContext) {
        Basket basket = checkoutContext.getBasket();
        retailTotal = 0.0;

        for (BasketItem basketItem: basket.getItems()) {
            int quantity = basketItem.getQuantity();
            double price = pricingService.getPrice(basketItem.getProductCode());
            basketItem.setProductRetailPrice(price);
            retailTotal += quantity*price;
            if(basketItem.getPromotion()!=null) {
            this.applyPromotion(basketItem.getPromotion(), basketItem, basketItem.getProductRetailPrice());
            }
        }

        checkoutContext.setRetailPriceTotal(retailTotal);
    }

    public double applyPromotion(Promotion promotion, BasketItem item, double price) {
        /*
         * Implement applyPromotion method
         */
    	
    	switch(promotion.getPromotion()) {
    	
    	case TWOFORONE:
    		int inteiro=item.getQuantity()/2;
    		
    		retailTotal-=price*inteiro;
    		break;
    		
    	case FIFTYOFF:
    		retailTotal-=price*item.getQuantity()*0.50;
    		break;
    	
    	case TENOFF:
    		retailTotal-=price*item.getQuantity()*0.10;
    		break;
    	}
    	DecimalFormat numberFormat = new DecimalFormat("#.00");
    	
    	    	
        return Double.parseDouble(numberFormat.format(retailTotal));
    }
}
