package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import at.ac.fhcampuswien.fhmdb.datalayer.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import org.h2.jdbc.JdbcSQLException;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.stream.Collectors;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label();
    private final JFXButton detailBtn = new JFXButton("Show Details");
    private final JFXButton addToWatchlistBtn = new JFXButton("Add to watchlist");
    private final VBox layout = new VBox(title, detail, genre, addToWatchlistBtn, detailBtn);
    private boolean collapsedDetails = true;

    private final boolean isWatchlistCell;

    WatchlistRepository repository = new WatchlistRepository();

    public MovieCell(boolean isWatchlistCell) {
        super();
        this.isWatchlistCell = isWatchlistCell;
        // color scheme
        detailBtn.setStyle("-fx-background-color: #f5c518;");
        detailBtn.setPrefWidth(120);
        addToWatchlistBtn.setStyle("-fx-background-color: #f5c518;");
        addToWatchlistBtn.setPrefWidth(120);
        title.getStyleClass().add("text-yellow");
        detail.getStyleClass().add("text-white");
        genre.getStyleClass().add("text-white");
        genre.setStyle("-fx-font-style: italic");
        layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null)));

        // layout
        title.fontProperty().set(title.getFont().font(20));
        detail.setWrapText(true);
        layout.setPadding(new Insets(10));
        layout.spacingProperty().set(10);
        layout.alignmentProperty().set(javafx.geometry.Pos.CENTER_LEFT);

        detailBtn.setOnMouseClicked(mouseEvent -> {
            if (collapsedDetails) {
                layout.getChildren().add(getDetails());
                collapsedDetails = false;
                detailBtn.setText("Hide Details");
            } else {
                layout.getChildren().remove(5);
                collapsedDetails = true;
                detailBtn.setText("Show Details");
            }
            setGraphic(layout);
        });

     /*   addToWatchlistBtn.setOnMouseClicked(mouseEvent -> {

                try {
                    repository.addToWatchlist(getItem());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }

        });


      */
        addToWatchlistBtn.setText(isWatchlistCell ? "Remove" : "Add to watchlist");
        addToWatchlistBtn.setOnMouseClicked(mouseEvent -> {
            if (isWatchlistCell) {
                try {
                    repository.removeFromWatchlist(getItem());

                    FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("watchlist-view.fxml"));
                    Parent root = FXMLLoader.load(fxmlLoader.getLocation());
                    Scene scene = addToWatchlistBtn.getScene();
                    scene.setRoot(root);



                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);


                }


            } else {
                try {
                    repository.addToWatchlist(getItem());
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        });

    }

    private VBox getDetails() {
        VBox details = new VBox();
        Label releaseYear = new Label("Release Year: " + getItem().getReleaseYear());
        Label length = new Label("Length: " + getItem().getLengthInMinutes() + " minutes");
        Label rating = new Label("Rating: " + getItem().getRating() + "/10");

        Label directors = new Label("Directors: " + String.join(", ", getItem().getDirectors()));
        Label writers = new Label("Writers: " + String.join(", ", getItem().getWriters()));
        Label mainCast = new Label("Main Cast: " + String.join(", ", getItem().getMainCast()));


        releaseYear.getStyleClass().add("text-white");
        length.getStyleClass().add("text-white");
        rating.getStyleClass().add("text-white");
        directors.getStyleClass().add("text-white");
        writers.getStyleClass().add("text-white");
        mainCast.getStyleClass().add("text-white");


        if (isWatchlistCell){
            details.getChildren().add(releaseYear);
            details.getChildren().add(rating);
            details.getChildren().add(length);
        } else {
            details.getChildren().add(releaseYear);
            details.getChildren().add(rating);
            details.getChildren().add(length);
            details.getChildren().add(directors);
            details.getChildren().add(writers);
            details.getChildren().add(mainCast);
        }





        return details;
    }
    @Override
    protected void updateItem(Movie movie, boolean empty) {
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setGraphic(null);
            setText(null);
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null
                            ? movie.getDescription()
                            : "No description available"
            );

            String genres = movie.getGenres()
                    .stream()
                    .map(Enum::toString)
                    .collect(Collectors.joining(", "));
            genre.setText(genres);

            detail.setMaxWidth(this.getScene().getWidth() - 30);

            setGraphic(layout);
        }
    }
}

