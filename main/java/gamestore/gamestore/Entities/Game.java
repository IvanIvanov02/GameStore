package gamestore.gamestore.Entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "games")
public class Game extends BaseEntity {

    @Column
    private String title;
    @Column
    private String trailer;
    @Column(name = "image_thumbnail")
    private String imageThumbnail;
    @Column
    private double size;
    @Column
    private double price;
    @Column
    private String description;
    @Column
    private LocalDate date;

    public Game() { }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public String getImageThumbnail() {
        return imageThumbnail;
    }

    public void setImageThumbnail(String imageThumbnail) {
        this.imageThumbnail = imageThumbnail;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
