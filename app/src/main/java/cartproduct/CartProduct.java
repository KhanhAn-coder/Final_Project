package cartproduct;

public class CartProduct {
    private String productName;
    private String productID;
    private int price;
    private String sourceID;
    private int quantity;
    private String shopID;
    private int Sales;
    private int Limit;
    private String type;


    public CartProduct(){

    }

    public CartProduct(String productName, String productID, int price, String sourceID, int quantity, String shopID, int sales, int limit, String type) {
        this.productName = productName;
        this.productID = productID;
        this.price = price;
        this.sourceID = sourceID;
        this.quantity = quantity;
        this.shopID = shopID;
        Sales = sales;
        Limit = limit;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public int getSales() {
        return Sales;
    }

    public void setSales(int sales) {
        Sales = sales;
    }

    public int getLimit() {
        return Limit;
    }

    public void setLimit(int limit) {
        Limit = limit;
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
