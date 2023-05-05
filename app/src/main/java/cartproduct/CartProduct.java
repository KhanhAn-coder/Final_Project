package cartproduct;

public class CartProduct {
    private String productName;
    private String productID;
    private int price;
    private String sourceID;
    private int quantity;
    public CartProduct(){

    }

    public CartProduct(String productName, String productID, int price, String sourceID, int quantity) {
        this.productName = productName;
        this.productID = productID;
        this.price = price;
        this.sourceID = sourceID;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
