package at.ac.fhcampuswien.fhmdb.Exceptions;

import javafx.scene.control.Alert;

import java.sql.SQLException;

public class DatabaseException extends SQLException {

    public DatabaseException(){}

    public DatabaseException(String message) {
        super(message);
    }

    public DatabaseException(Throwable cause) {
        super(cause);
    }

    public DatabaseException(String message, Throwable cause) {
        super(message, cause);
    }

    /*public void throwAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("An error has occurred.");
        alert.setContentText("Error while loading./nDatabaseException");
    }

     */
}
