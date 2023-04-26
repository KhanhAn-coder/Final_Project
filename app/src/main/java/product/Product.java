package product;

public class Product {
    private String title;
    private int price;
    private double rating;
    private double sold;
    private int sourceID;
    private String type;

    public Product(String title, int price, double rating, double sold, int sourceID, String type) {
        this.title = title;
        this.price = price;
        this.rating = rating;
        this.sold = sold;
        this.sourceID = sourceID;
        this.type = type;
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

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public int getSourceID() {
        return sourceID;
    }

    public void setSourceID(int sourceID) {
        this.sourceID = sourceID;
    }

    public String getType(){return type;}

    public void setType(String type){this.type = type;}
}

