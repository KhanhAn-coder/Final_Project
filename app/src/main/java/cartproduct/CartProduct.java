package cartproduct;

public class CartProduct {
    private int resourceID;
    private String title;
    private int price;
    private int amount;

    public int getResourceID() {
        return resourceID;
    }

    public void setResourceID(int resourceID) {
        this.resourceID = resourceID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public CartProduct(int resourceID, String title, int price, int amount) {
        this.resourceID = resourceID;
        this.title = title;
        this.price = price;
        this.amount = amount;
    }
}
