package at.ac.fhcampuswien.fhmdb.Exceptions;

import javafx.scene.control.Alert;

import java.io.IOException;

public class MovieApiException extends IOException {

    public MovieApiException(){}

    public MovieApiException(String message) {
        super(message);
    }

    public MovieApiException(Throwable cause) {
        super(cause);
    }

    public MovieApiException(String message, Throwable cause) {
        super(message, cause);
    }

  /*  public void throwAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred.");
        alert.setContentText("Http error.");
    }

   */

}

