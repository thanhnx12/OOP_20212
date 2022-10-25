package project.model;

public class SoldProduct{
    Product product;
    int count;
    int price;
    public SoldProduct(Product product, int count, int price) {
        this.product = product;
        this.count = count;
        this.price = price;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    public int getMoney(){
        return count*price;
    }
    @Override
    public String toString() {
        return "SoldProduct{" +
                "product=" + product +
                ", count=" + count +
                ", price=" + price +
                '}';
    }
    public String getIdProduct(){
        return product.getId();
    }
    public String getNameProduct(){
        return product.getName();
    }

    public int getPriceProduct(){
        return product.getPrice();
    }

}
