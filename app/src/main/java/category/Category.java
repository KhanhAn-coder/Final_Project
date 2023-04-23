package category;

import java.util.ArrayList;

import product.Product;

public class Category {
    private String nameCategory;
    private ArrayList<Product> products;

    public Category(String nameCategory, ArrayList<Product> products) {
        this.nameCategory = nameCategory;
        this.products = products;
    }

    public String getNameCategory() {
        return nameCategory;
    }

    public void setNameCategory(String nameCategory) {
        this.nameCategory = nameCategory;
    }

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }
}
