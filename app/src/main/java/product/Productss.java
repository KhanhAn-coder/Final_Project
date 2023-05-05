package product;

public class Productss {
    private String title;
    private int price;
    private double rating;
    private double sold;
    private String sourceID;
    private String type;
    private String description;
    private String productID;

    public Productss() {

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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(double sold) {
        this.sold = sold;
    }

    public String getSourceID() {
        return sourceID;
    }

    public void setSourceID(String sourceID) {
        this.sourceID = sourceID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Productss(String title, int price, double rating, double sold, String sourceID, String type, String description, String productID) {
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.sold = sold;
        this.sourceID = sourceID;
        this.type = type;
        this.description = description;
        this.productID = productID;
    }
}
