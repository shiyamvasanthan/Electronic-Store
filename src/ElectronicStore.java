//Class representing an electronic store
//Has an array of products that represent the items the store can sell

import java.util.*;

public class ElectronicStore {
    //Store name, revenue, and number of sales
    private String name;
    private double revenue;
    private int numberSales;
    //Arraylist of the store's products
    private List<Product> products;
    //Hashmap that stores the amount of each product in the cart
    private HashMap<Product, Integer> cartItems;
    //Value of the cart in $
    private double cartAmount;

    //Constructor
    public ElectronicStore(String initName) {
        this.name = initName;
        this.revenue = 0.0;
        this.numberSales = 0;
        this.products = new ArrayList<Product>();
        this.cartItems = new HashMap<Product, Integer>();
        this.cartAmount = 0.0;
    }

    //Getter methods
    public String getName() { return name; }

    public Double getRevenue() { return revenue; }

    public int getNumberSales() { return numberSales; }

    public Double getCartAmount(){ return cartAmount; }

    //Returns the store's dollar per sale
    public Double getDollarPerSale(){ return revenue/numberSales; }

    //Method that displays all the store's products in the stock listview
    //If the user has added all of a product to the cart, or if it's no longer in stock, don't display it
    public List<Product> getStock() {
        //Create new list of products
        List<Product> stockList = new ArrayList<Product>();

        //Iterate through the products in the store
        for (Product product : products){
            //If the product is not in the cart yet (Prevents null pointer exception) or the quantity in the cart is less than the stock quantity of the product
            //And the stock quantity of the product is greater than zero
            if ((cartItems.get(product) == null || cartItems.get(product) < product.getStockQuantity()) && product.getStockQuantity() > 0){
                //Add the product to the list
                stockList.add(product);
            }
        }

        //Return the list
        return stockList;
    }

    //Method that displays the current store cart in the format "# x product"
    public List<String> getCart(){
        //Create a new list of strings
        List<String> items = new ArrayList<String>();

        //Iterate through all the items in the cart
        for (Product p : cartItems.keySet()) {
            //Add to the list, the number of times the item is in the cart, and the item itself
            items.add(cartItems.get(p) + " x " + p);
        }

        //Return the list
        return items;
    }

    //Method which displays the store's top 3 most popular items (Most sold quantity)
    public List<Product> mostPopularItems(){
        //Create a new list that is a copy of the store's products
        List<Product> mostPopularList = new ArrayList<Product>(products);

        //Sort the arraylist of products by the most sold quantity (Product class implements Comparable)
        Collections.sort(mostPopularList);

        //Remove the items from the list of products not in the top 3
        for (int i = mostPopularList.size() - 1; i >= 3; i--){
            mostPopularList.remove(i);
        }

        //Return the list of top 3 products
        return mostPopularList;
    }

    //When the add to cart button is pressed, add one of the product to the cart
    public void addCart(Product p){
        //If the cart already has that selected item
        if (cartItems.containsKey(p)) {
            //Add one to the quantity of that product in the cart
            cartItems.put(p, cartItems.get(p) + 1);
        }
        //However, if the cart listview doesn't already have that item
        else {
            //Put the item in the cart with a quantity of one
            cartItems.put(p, 1);
        }

        //Add the product's price to the total value of the cart
        cartAmount += p.getPrice();
    }

    //When the remove from cart button is pressed, remove one item from the cart
    public void removeCart(String s){
        //Iterate through all the items in the cart
        for (Product p : cartItems.keySet()){
            //If the cart string ("# x product") contains the product toString()
            if (s.contains(p.toString())){
                //If there is more than one of the selected item in the cart
                if (cartItems.get(p) > 1){
                    //Remove just one of the item from the cart
                    cartItems.put(p, cartItems.get(p) - 1);
                }
                //However, if there's only one of the item in the cart left
                else {
                    //Remove it from the cart completely
                    cartItems.remove(p);
                }

                //Remove the product's price from the total value of the cart
                cartAmount -= p.getPrice();
            }
        }
    }

    //Method when the user clicks the complete sale button, it will complete the transaction
    public void completeTransaction(){
        //Add the value of the cart to the store's total revenue
        revenue += cartAmount;

        //Iterate through the items in the cart
        for (Product p : cartItems.keySet()){
            //Sell each item by the amount in the cart
            p.sellUnits(cartItems.get(p));
        }

        //Clear the cart
        cartItems.clear();

        //Set the value of the cart to zero because its empty now
        cartAmount = 0;

        //Add one to the total number of sales
        numberSales += 1;
    }

    //Add product method
    public boolean addProduct(Product newProduct) {
        //Add the product to the list of the store's products
        products.add(newProduct);

        //Return true to indicate product has been successfully added
        return true;
    }

    //Create store method
    public static ElectronicStore createStore() {
        ElectronicStore store1 = new ElectronicStore("Watts Up Electronics");
        Desktop d1 = new Desktop(100, 10, 3.0, 16, false, 250, "Compact");
        Desktop d2 = new Desktop(200, 10, 4.0, 32, true, 500, "Server");
        Laptop l1 = new Laptop(150, 10, 2.5, 16, true, 250, 15);
        Laptop l2 = new Laptop(250, 10, 3.5, 24, true, 500, 16);
        Fridge f1 = new Fridge(500, 10, 250, "White", "Sub Zero", false);
        Fridge f2 = new Fridge(750, 10, 125, "Stainless Steel", "Sub Zero", true);
        ToasterOven t1 = new ToasterOven(25, 10, 50, "Black", "Danby", false);
        ToasterOven t2 = new ToasterOven(75, 10, 50, "Silver", "Toasty", true);
        store1.addProduct(d1);
        store1.addProduct(d2);
        store1.addProduct(l1);
        store1.addProduct(l2);
        store1.addProduct(f1);
        store1.addProduct(f2);
        store1.addProduct(t1);
        store1.addProduct(t2);
        return store1;
    }
} 