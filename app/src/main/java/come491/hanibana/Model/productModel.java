package come491.hanibana.Model;

public class productModel {
    String id;
    String productName;
    String productCategory;
    String productCategoryId;
    String productSubCategory;
    String productSubCategoryId;
    String productDescription;
    String productImage;
    Double productPrice;

    public productModel(String id, String productName, String productCategory, String productCategoryId, String productSubCategory, String productSubCategoryId, String productDescription, String productImage, Double productPrice) {
        this.id = id;
        this.productName = productName;
        this.productCategory = productCategory;
        this.productCategoryId = productCategoryId;
        this.productSubCategory = productSubCategory;
        this.productSubCategoryId = productSubCategoryId;
        this.productDescription = productDescription;
        this.productImage = productImage;
        this.productPrice = productPrice;
    }

    public productModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(String productCategory) {
        this.productCategory = productCategory;
    }

    public String getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(String productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public String getProductSubCategory() {
        return productSubCategory;
    }

    public void setProductSubCategory(String productSubCategory) {
        this.productSubCategory = productSubCategory;
    }

    public String getProductSubCategoryId() {
        return productSubCategoryId;
    }

    public void setProductSubCategoryId(String productSubCategoryId) {
        this.productSubCategoryId = productSubCategoryId;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public Double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(Double productPrice) {
        this.productPrice = productPrice;
    }
}
