//Base class for all products the store will sell
public abstract class Product implements Comparable<Product>{
    private double price;
    private int stockQuantity;
    private int soldQuantity;

    public Product(double initPrice, int initQuantity) {
        price = initPrice;
        stockQuantity = initQuantity;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public double getPrice() {
        return price;
    }

    //Returns the total revenue (price * amount) if there are at least amount items in stock
    //Return 0 otherwise (i.e., there is no sale completed)
    public double sellUnits(int amount) {
        if (amount > 0 && stockQuantity >= amount) {
            stockQuantity -= amount;
            soldQuantity += amount;
            return price * amount;
        }
        return 0.0;
    }

    //Compare to method, compares the sold quantity of two products
    public int compareTo(Product p) {
        return p.soldQuantity - this.soldQuantity;
    }
}