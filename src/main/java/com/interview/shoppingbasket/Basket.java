package com.interview.shoppingbasket;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> items = new ArrayList<>();

    public void add(String productCode, String productName, int quantity) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);

        items.add(basketItem);
    }
    
    public void add(String productCode, String productName, int quantity, double productRetailPrice) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);
        basketItem.setProductRetailPrice(productRetailPrice);

        items.add(basketItem);
    }
    
    public void add(String productCode, String productName, int quantity, double productRetailPrice, Promotion promotion) {
        BasketItem basketItem = new BasketItem();
        basketItem.setProductCode(productCode);
        basketItem.setProductName(productName);
        basketItem.setQuantity(quantity);
        basketItem.setProductRetailPrice(productRetailPrice);
        basketItem.setPromotion(promotion);

        items.add(basketItem);
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void consolidateItems() {
        // Exercise - implement this function
    	for(int i=0;i<items.size()-1;i++) {
    		for(int j=i+1;j<items.size();j++) {
    			if(items.get(i).getProductCode().equals(items.get(j).getProductCode())&& items.get(j).getQuantity()>0) {
    				items.get(i).setQuantity(items.get(i).getQuantity()+items.get(j).getQuantity());
    				items.get(j).setQuantity(0);
    			}
    		}
    	}
    	List<BasketItem> temp = new ArrayList<>();
    	for(BasketItem item:items) {
    		if(item.getQuantity()!=0) {
    			temp.add(item);
    		}
    	}
    	items=temp;
    }
}

