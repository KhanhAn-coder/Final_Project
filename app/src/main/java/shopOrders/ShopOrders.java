package shopOrders;

public class ShopOrders {
    private String address, name, phone;
    private int totalAmount;

    public ShopOrders(){

    }

    public ShopOrders(String address, String name, String phone, int totalAmount) {
        this.address = address;
        this.name = name;
        this.phone = phone;
        this.totalAmount = totalAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }
}
