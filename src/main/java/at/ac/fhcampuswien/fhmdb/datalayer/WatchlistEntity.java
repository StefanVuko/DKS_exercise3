package at.ac.fhcampuswien.fhmdb.datalayer;

import at.ac.fhcampuswien.fhmdb.models.Genre;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.util.List;

@DatabaseTable(tableName = "Movie")
public class WatchlistEntity {
    @DatabaseField(generatedId = true)
    private long ID;

    @DatabaseField
    private String ApiID;

    @DatabaseField
    private String title;

    @DatabaseField
    private String description;

    @DatabaseField
    private String genres;

    @DatabaseField
    private int releaseYear;

    @DatabaseField
    private String imgURL;

    @DatabaseField
    private int lengthInMinutes;

    @DatabaseField
    private double rating;

    public WatchlistEntity()
    {

    }
    public WatchlistEntity(String ApiID, String title, String description, String genres, int releaseYear, String imgURL, int lengthInMinutes, double rating)
    {
        this.ApiID = ApiID;
        this.title = title;
        this.description = description;
        this.genres = genres;
        this.releaseYear = releaseYear;
        this.imgURL = imgURL;
        this.lengthInMinutes = lengthInMinutes;
        this.rating = rating;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getApiID() {
        return ApiID;
    }

    public void setApiID(String apiID) {
        ApiID = apiID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public String getImgURL() {
        return imgURL;
    }

    public void setImgURL(String imgURL) {
        this.imgURL = imgURL;
    }

    public int getLengthInMinutes() {
        return lengthInMinutes;
    }

    public void setLengthInMinutes(int lengthInMinutes) {
        this.lengthInMinutes = lengthInMinutes;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static String genresToString(List<Genre> genres)
    {
        String result = "";
        for (var item: genres) {
            result += item.toString() + ",";
        }
        return result;
    }
}
