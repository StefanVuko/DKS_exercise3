package at.ac.fhcampuswien.fhmdb.models;

import at.ac.fhcampuswien.fhmdb.contoller.WatchlistViewController;

@FunctionalInterface
public interface ClickEventHandler<T> {
    void onClick(T t);
}
