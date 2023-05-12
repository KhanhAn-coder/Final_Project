package userOrders;

public class UserOrdersObject {
    private String shopID;
    private String phoneNumber;
    private String address;
    private String status;
    private int totalPrice;

    public UserOrdersObject() {

    }

    public UserOrdersObject(String shopID, String phoneNumber, String address, String status, int totalPrice) {
        this.shopID = shopID;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
        this.totalPrice = totalPrice;
    }

    public String getShopID() {
        return shopID;
    }

    public void setShopID(String shopID) {
        this.shopID = shopID;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
