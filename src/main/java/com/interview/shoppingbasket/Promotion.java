package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;

public class Promotion implements PromotionsService {
    private PromotionType promotion;

	public Promotion(PromotionType promotion) {
		this.promotion = promotion;
	}

	public PromotionType getPromotion() {
		return promotion;
	}

	public void setPromotion(PromotionType promotion) {
		this.promotion = promotion;
	}

	@Override
	public List<Promotion> getPromotions(Basket basket) {
		List<Promotion> promotions= new ArrayList<Promotion>();
		
		for(int i=0;i<basket.getItems().size();i++) {
			promotions.add(basket.getItems().get(i).getPromotion());
		}
		return promotions;
	}
    
    
	
	
}
