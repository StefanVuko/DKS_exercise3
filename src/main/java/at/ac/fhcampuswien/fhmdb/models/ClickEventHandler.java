package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.contoller.WatchlistViewController;
import javafx.scene.control.Button;

@FunctionalInterface
public interface ClickEventHandler<T> {
    void onClick(T t, boolean isWatchlistCell, Button watchBtn);
}
