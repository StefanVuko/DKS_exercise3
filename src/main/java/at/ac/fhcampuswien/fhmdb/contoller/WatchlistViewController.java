package at.ac.fhcampuswien.fhmdb.contoller;

import at.ac.fhcampuswien.fhmdb.Exceptions.DatabaseException;
import at.ac.fhcampuswien.fhmdb.FhmdbApplication;
import at.ac.fhcampuswien.fhmdb.datalayer.WatchlistEntity;
import at.ac.fhcampuswien.fhmdb.datalayer.WatchlistRepository;
import at.ac.fhcampuswien.fhmdb.models.ClickEventHandler;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import org.h2.jdbc.JdbcSQLException;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;

public class WatchlistViewController {
    @FXML
    public Button watchlistBtn;
    @FXML
    public VBox mainPane;
    @FXML
    public JFXListView movieWatchlistView;
    WatchlistRepository repo;

    public void initialize() {
        System.out.println("WatchlistViewController initialized");

        repo = new WatchlistRepository();
        List<WatchlistEntity> watchlist = new ArrayList<>();

        try {
            watchlist = repo.getAll();
        } catch (SQLException e) {
            MovieCell.showExceptionDialog(new DatabaseException("Database problem"));
        }

        ObservableList<Movie> movies = FXCollections.observableArrayList(
                watchlist.stream()
                        .map(WatchlistEntity::toMovie)
                        .collect(Collectors.toList())
        );

        movieWatchlistView.setItems(movies);
        movieWatchlistView.setCellFactory(movieListView -> new MovieCell(true));
    }

    public void loadHomeView() {
        FXMLLoader fxmlLoader = new FXMLLoader(FhmdbApplication.class.getResource("home-view.fxml"));
        try{
            Scene scene = new Scene(fxmlLoader.load(), 890, 620);
            Stage stage = (Stage)mainPane.getScene().getWindow();
            stage.setScene(scene);

        } catch (IOException e) {
            MovieCell.showExceptionDialog(new IllegalArgumentException("Error while loading"));
        }
    }


}
