package project.model;

public class Book extends Product {
    private String author;
    private String language;
    private String translator;
    private int numberOfPages;
    private String publishingCompany;
    private int publishingYear;

    public Book(String id, String name, int price, String category, String status, int importPrice, int count, String description, String author, String language, String translator, int numberOfPages, String publishingCompany, int publishingYear) {
        super(id, name, price, category, importPrice, count, description,status);
        this.author = author;
        this.language = language;
        this.translator = translator;
        this.numberOfPages = numberOfPages;
        this.publishingCompany = publishingCompany;
        this.publishingYear = publishingYear;
    }

    public Book(){};
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getTranslator() {
        return translator;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public String getPublishingCompany() {
        return publishingCompany;
    }

    public void setPublishingCompany(String publishingCompany) {
        this.publishingCompany = publishingCompany;
    }

    public int getPublishingYear() {
        return publishingYear;
    }

    public void setPublishingYear(int publishingYear) {
        this.publishingYear = publishingYear;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", importPrice=" + importPrice +
                ", count=" + count +
                ", description" + description +
                ", author='" + author + '\'' +
                ", language='" + language + '\'' +
                ", translator='" + translator + '\'' +
                ", numberOfPages=" + numberOfPages +
                ", publishingCompany='" + publishingCompany + '\'' +
                ", publishingYear=" + publishingYear +
                '}';
    }

}
