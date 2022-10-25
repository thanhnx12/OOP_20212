package project.model;

public class Product {
    protected String id;
    protected String name;
    protected int price;
    protected String category;
    protected int importPrice;
    protected int count;
    protected String description;
    protected String status;
    public Product(){};
    public Product(String id, String name, int price, String category, int importPrice, int count,String description,String status) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.category = category;
        this.importPrice = importPrice;
        this.count = count;
        this.description = description;
        this.status=status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getImportPrice() {
        return importPrice;
    }

    public void setImportPrice(int importPrice) {
        this.importPrice = importPrice;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", importPrice=" + importPrice +
                ", count=" + count +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
