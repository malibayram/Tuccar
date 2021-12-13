package come491.hanibana.Model;

import java.util.ArrayList;

public class categoryModel {
    String id;
    String categoryName;
    String categoryImage;
    ArrayList<productModel> products;

    public categoryModel(String id, String categoryName, String categoryImage, ArrayList<productModel> products) {
        this.id = id;
        this.categoryName = categoryName;
        this.categoryImage = categoryImage;
        this.products = products;
    }

    public categoryModel() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryImage() {
        return categoryImage;
    }

    public void setCategoryImage(String categoryImage) {
        this.categoryImage = categoryImage;
    }

    public ArrayList<productModel> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<productModel> products) {
        this.products = products;
    }
}
