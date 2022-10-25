package project.model;

public class Music extends Product {
    private String singers;
    private String producer;
    private String musicGenre;
    private String time;

    public Music(String id, String name, int price, String category, int importPrice, int count, String description, String status,String singers, String producer, String musicGenre, String time) {
        super(id, name, price, category, importPrice, count, description, status);
        this.singers = singers;
        this.producer = producer;
        this.musicGenre = musicGenre;
        this.time = time;
    }

    public Music(){};

    public String getSingers() {
        return singers;
    }

    public void setSingers(String singers) {
        this.singers = singers;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getMusicGenre() {
        return musicGenre;
    }

    public void setMusicGenre(String musicGenre) {
        this.musicGenre = musicGenre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Music{" +
                ", id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", importPrice=" + importPrice +
                ", description=" + description +
                ", count=" + count +
                ", singers='" + singers + '\'' +
                ", producer='" + producer + '\'' +
                ", musicGenre='" + musicGenre + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
