package come491.hanibana.Model;

import java.util.List;

public class orderModel {
    String id;
    String purchaseDate;
    String orderNumber;
    String amount;
    String paymentType;
    String deliveryAddress;
    List<productModel> products;

    public orderModel(String id, String purchaseDate, String orderNumber, String amount, String paymentType, String deliveryAddress, List<productModel> products) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.orderNumber = orderNumber;
        this.amount = amount;
        this.paymentType = paymentType;
        this.deliveryAddress = deliveryAddress;
        this.products = products;
    }

    public orderModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public List<productModel> getProducts() {
        return products;
    }

    public void setProducts(List<productModel> products) {
        this.products = products;
    }
}
