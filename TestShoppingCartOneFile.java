//*******************************************************************
// NOTE: Shopping Cart Program by Dennis B. Morales
//*******************************************************************


import java.lang.Math; // headers MUST be above the first class
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.math.BigDecimal;
import java.math.RoundingMode;




// one class needs to have a main() method
public class TestShoppingCart
{
  public static final String ULT_SMALL = "ult_small"; 
  public static final String ULT_SMALL_DESC = "Unlimited 1 GB";
  public static final double ULT_SMALL_PRICE = 24.90;
  
  public static final String ULT_MEDIUM = "ult_medium";
  public static final String ULT_MEDIUM_DESC = "Unlimited 2 GB";
  public static final double ULT_MEDIUM_PRICE = 29.90;
  
  public static final String ULT_LARGE = "ult_large";
  public static final String ULT_LARGE_DESC = "Unlimited 5 GB";
  public static final double ULT_LARGE_PRICE = 44.90;
  
  public static final String DATA_PACK = "1gb";
  public static final String DATA_PACK_DESC = "1 GB Data-pack";
  public static final double DATA_PACK_PRICE = 9.90;
  
  public static void cartOrder1() {
    System.out.println("Scenario 1");
    ShoppingCart cart = new ShoppingCart();
    Item item1 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item2 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item3 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    
    Item item5GB1 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    
    // scenario 1, apply 3 for 2 promo
    cart.add(item1);
    cart.add(item2);
    cart.add(item3);
    cart.add(item5GB1);
    System.out.println("Cart Total = $" + cart.total());
    cart.items();
    cart.empty();
  }
  
  public static void cartOrder2() {
    System.out.println("Scenario 2");
    ShoppingCart cart = new ShoppingCart();
    
    Item item1 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item2 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    
    Item item5GB1 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    Item item5GB2 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    Item item5GB3 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    Item item5GB4 = new Item(ULT_LARGE,ULT_LARGE_DESC,ULT_LARGE_PRICE,1);
    
    // scenario 2, apply bulk discount rate
    cart.add(item1);
    cart.add(item2);
    cart.add(item5GB1);
    cart.add(item5GB2);
    cart.add(item5GB3);
    cart.add(item5GB4);
    System.out.println("Cart Total = $" + cart.total());
    cart.items();
    cart.empty();
  }
  
  public static void cartOrder3() {
    System.out.println("Scenario 3");
    ShoppingCart cart = new ShoppingCart();
    Item item1 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item2 = new Item(ULT_MEDIUM, ULT_MEDIUM_DESC,ULT_MEDIUM_PRICE,1);
    Item item3 = new Item(ULT_MEDIUM, ULT_MEDIUM_DESC,ULT_MEDIUM_PRICE,1);
    
    // scenario 3, apply promo code
    cart.add(item1);
    cart.add(item2);
    cart.add(item3);
    System.out.println("Cart Total = $" + cart.total());
    cart.items();
    cart.empty();
    
  }
  
  public static void cartOrder4() {
    System.out.println("Scenario 4");
    ShoppingCart cart = new ShoppingCart();
    Item item1 = new Item(ULT_SMALL, ULT_SMALL_DESC,ULT_SMALL_PRICE,1);
    Item item2 = new Item(DATA_PACK, DATA_PACK_DESC,DATA_PACK_PRICE,1);
    
    
    // scenario 4, apply promo code
    cart.add(item1);
    // apply PROMO CODE
    cart.add(item2,"I<3AMAYSIM");
    System.out.println("Cart Total = $" + cart.total());
    cart.items();
    cart.empty();
    
  }
  
  
  
  public static void main(String[] args)
  {
    // Define the rules
    // 3 for 2 deal on unlimited 1 GB
    Rules rule1 = new Rules(3,2,24.90);
    PricingRules.add(ULT_SMALL, rule1);
    
    // bulk discount for 5 gig
    Rules rule2 = new Rules(4,4,39.90);
    PricingRules.add(ULT_LARGE, rule2);
    
    // add bundle rules for medium
    Rules rule3 = new Rules(1,DATA_PACK_DESC);
    BundleRules.add(ULT_MEDIUM, rule3);
    
    // scenario 1, apply 3 for 2 promo
    cartOrder1();
    
    // scenario 2, buy more than 3 get discount price
    cartOrder2();
    
    // scenario 3, get additional pack
    cartOrder3();
    
    // scenario 4, apply promo code
    cartOrder4();
    
  }
}




public class Item implements java.io.Serializable{
  private String itemId;
  private String itemName;
  private double itemPrice;
  private int itemQuantity;
  
  public Item() {
  }
  
  public Item(String itemId, String itemName, double itemPrice, int itemQuantity) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
  }
  
  public void setItem(String itemId, String itemName, double itemPrice, int itemQuantity) {
    this.itemId = itemId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
    this.itemQuantity = itemQuantity;
  }
  
  
  public String getItemId() {
    return this.itemId;
  }
  
  public int getItemQuantity() {
    return this.itemQuantity;
  }
  
  public double getItemPrice() {
    return this.itemPrice;
  }
  
  public String getItemName() {
    return this.itemName;
  }
  
  // this can also be used to calculate accumulation of orders
  public void setItemPrice(double itemPrice) {
    this.itemPrice = itemPrice;
  }
  
  public void setItemQuantity(int itemQuantity) {
    this.itemQuantity = itemQuantity;
  }
  
}




public class ShoppingCart {
  public static final String PROMO_CODE = "I<3AMAYSIM"; // just declare it constant for simplicity
  public static final double PROMO_PERCENT = .10; // 10% DISCOUNT 
  private Map<String,Item> map = new ConcurrentHashMap<String,Item>();
  private boolean applyPromo = false;
  
  public ShoppingCart() {
    if (map == null) {
      map = new ConcurrentHashMap<String,Item>();
    }
  }
  
  public boolean add(Item newItem) {
    if (map != null) {
      System.out.println("Added 1 " + newItem.getItemName());
      if (map.containsKey(newItem.getItemId())) {
        
        // update item order
        Item item = (Item)map.get(newItem.getItemId());
        item.setItemQuantity(item.getItemQuantity() + newItem.getItemQuantity());
        item.setItemPrice( newItem.getItemPrice());
        return true;
      }
      // add new item
      map.put(newItem.getItemId(),newItem);
      return false;
    }
    return false; // error map is not initialized
  }
  
  public boolean addFree(Item newItem) {
    if (map != null) {
      //System.out.println("Added 1 " + newItem.getItemName());
      if (map.containsKey(newItem.getItemId())) {
        
        // update item order
        Item item = (Item)map.get(newItem.getItemId());
        item.setItemQuantity(item.getItemQuantity() + newItem.getItemQuantity());
        item.setItemPrice( newItem.getItemPrice());
        return true;
      }
      // add new item
      map.put(newItem.getItemId(),newItem);
      return false;
    }
    return false; // error map is not initialized
  }
  
  public void add(Item item, String promoCode) {
    add(item);
    if (PROMO_CODE.equals(promoCode)) {
      System.out.println("\'"+ promoCode + "\'" + "Promo Applied");
      setApplyPromo(true);
    }
  }
  
  public void setApplyPromo(boolean value) {
    this.applyPromo = value;
  }
  
  public boolean isPromoToBeApplied() {
    return this.applyPromo;
  }
  
  public double total() {
    double totalAmount = 0.0;
    // iterate through all the items and calculate price
    Iterator iterator = map.values().iterator();
    double itemTotal = 0.0;
    while (iterator.hasNext()) {
      Item itm = ((Item)iterator.next());
      int itmQty = itm.getItemQuantity();
      while (itmQty>=1) {
        itemTotal += itm.getItemPrice();
        itmQty--;
      }
      
      totalAmount += itemTotal;
      
      totalAmount -= PricingRules.apply(itm.getItemId(),itm.getItemQuantity(), itm.getItemPrice(), itemTotal);
      
      itemTotal = 0.0;
    }
    // this is to be applied on top of everything
    if (isPromoToBeApplied()) {
      totalAmount = totalAmount - (totalAmount * PROMO_PERCENT);
    }
    
    BigDecimal a = new BigDecimal(totalAmount);
        BigDecimal b = a.setScale(2, RoundingMode.CEILING);
    return b.doubleValue();
    
  }
  
  
  public void items() {
    Iterator iterator = map.values().iterator();
    System.out.println("Cart Items");
    while (iterator.hasNext()) {
      Item itm = ((Item)iterator.next());
      int itemCount = itm.getItemQuantity();
      System.out.println(itemCount + " X " + itm.getItemName());
      
      while (itemCount > 0) {
        Rules rule = BundleRules.getRule(itm.getItemId());
        if (rule != null) {
          // add the free bundle now
          Item bundleItem = new Item("1gb", rule.getBundleItemName(),0.0,rule.getBundleQuantity());
          addFree(bundleItem);
          //System.out.println(itemCount + " X " + rule.getBundleItemName());
        }
        else {
          break; // no bundle rules found
        }
        itemCount--;
      }
      
      
    }
  }
  
  public void empty() {
    map.clear();
  }
  
  
}


public class Rules {
  
  private int discountQtytoQualify;
  private int discountQtyToBill;
  private double discountPrice;
  private int bundleQuantity;
  private String bundleItemName;
  
  
  public Rules() {
  }
  
  // define first rule 3 for the price of 2
  public Rules(int discountQtytoQualify, int discountQtyToBill, double price) {
    this.discountQtytoQualify = discountQtytoQualify;
    this.discountQtyToBill = discountQtyToBill;
    this.discountPrice = price;
    
  }
  
  public Rules(int bundleQuantity, String bundleItemName ) {
    this.bundleQuantity = bundleQuantity;
    this.bundleItemName = bundleItemName;
  }
  
  public int getDiscountQtytoQualify() {
    return this.discountQtytoQualify;
  }
  
  public int getDiscountQtyToBill() {
    return this.discountQtyToBill;
  }
  
  public double getDiscountPrice() {
    return this.discountPrice;
  }
  
  public String getBundleItemName() {
    return this.bundleItemName;
  }
  
  public int getBundleQuantity() {
    return this.bundleQuantity;
  }
  
}




public class BundleRules {
  private static Map<String,Rules> rulesMap = new ConcurrentHashMap<String,Rules>();
  
  public BundleRules() {
    if (rulesMap == null) {
      rulesMap = new ConcurrentHashMap<String,Rules>();
    }
  }
  
  static public Map<String,Rules> getRulesMap() {
    return rulesMap;
  }
  
  static public void add(String itemId, Rules rule) {
    rulesMap.put(itemId, rule);
    
  }
  
  static public Rules getRule(String itemId) {
    if (rulesMap.containsKey(itemId)) {
      return (Rules)rulesMap.get(itemId);
    }
    return null;
  }
}


public class PricingRules {
  private static Map<String,Rules> rulesMap = new HashMap<String,Rules>();
  
  public PricingRules() {
    if (rulesMap == null) {
      rulesMap = new HashMap<String,Rules>();
    }
  }
  
  static public Map<String,Rules> getRulesMap() {
    return rulesMap;
  }
  
  static public void add(String itemId, Rules rule) {
    rulesMap.put(itemId, rule);
    
  }
  
  static public double apply(String itemId,int itemQuantity, double itemPrice, double total) {
    double totalAmount = 0.0;
    // any pricing rules or discount
    switch (itemId) {
      case "ult_small" : 
      totalAmount = applyPricingRules(itemId,itemQuantity, itemPrice);
      break;
      case "ult_large" :
      totalAmount = applyBulkDiscountRules(itemId,itemQuantity, itemPrice, total);
      break;
    }
    return totalAmount;
  }
  
  private static double applyPricingRules(String itemId, int itemQuantity, double itemPrice) {
    double deductPrice = 0.0;
    if (PricingRules.getRulesMap().containsKey(itemId)) {
      Rules rule = (Rules)PricingRules.getRulesMap().get(itemId);
      
      
      // let's compute
      int processQuantity = itemQuantity;
      while (processQuantity >= rule.getDiscountQtytoQualify()) {
        processQuantity -= rule.getDiscountQtytoQualify();
        deductPrice += (itemPrice * (rule.getDiscountQtytoQualify() - rule.getDiscountQtyToBill()) );
      }
      
    }
    return deductPrice;
  }
  
  private static double applyBulkDiscountRules(String itemId, int itemQuantity, double itemPrice, double total) {
    double returnValue = 0.0;
    if (PricingRules.getRulesMap().containsKey(itemId)) {
      Rules rule = (Rules)PricingRules.getRulesMap().get(itemId);
      
      if (itemQuantity >= rule.getDiscountQtytoQualify()) {
        returnValue = itemQuantity * rule.getDiscountPrice();
        returnValue = total - returnValue;
          
      }
      
    }
    return returnValue;
  }
  
  
}