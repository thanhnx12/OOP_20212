package project.model;

public class Film extends Product {
    private String director;
    private String mainActor;
    private String movieGenre;
    private String time;
    private int premiere;
    private String language;

    public Film(String id, String name, int price, String category, int importPrice, int count, String description, String status,String director, String mainActor, String movieGenre, String time, int premiere, String language) {
        super(id, name, price, category, importPrice, count, description,status);
        this.director = director;
        this.mainActor = mainActor;
        this.movieGenre = movieGenre;
        this.time = time;
        this.premiere = premiere;
        this.language = language;
    }

    public Film(){};

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getMainActor() {
        return mainActor;
    }

    public void setMainActor(String mainActor) {
        this.mainActor = mainActor;
    }

    public String getMovieGenre() {
        return movieGenre;
    }

    public void setMovieGenre(String movieGenre) {
        this.movieGenre = movieGenre;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getPremiere() {
        return premiere;
    }

    public void setPremiere(int premiere) {
        this.premiere = premiere;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    @Override
    public String toString() {
        return "Film{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", category='" + category + '\'' +
                ", importPrice=" + importPrice +
                ", count=" + count +
                ", category=" + category +
                ", director='" + director + '\'' +
                ", mainActor='" + mainActor + '\'' +
                ", movieGenre='" + movieGenre + '\'' +
                ", time='" + time + '\'' +
                ", premiere=" + premiere +
                ", language='" + language + '\'' +
                '}';
    }
}
