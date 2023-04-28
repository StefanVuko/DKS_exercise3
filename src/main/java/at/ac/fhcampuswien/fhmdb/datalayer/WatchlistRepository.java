package at.ac.fhcampuswien.fhmdb.datalayer;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.List;

public class WatchlistRepository {
    Dao<WatchlistEntity, Long> dao;

    public WatchlistRepository()
    {
        this.dao = Database.getInstance().getDao();
    }
    List<WatchlistEntity> getAll() throws SQLException {
        return dao.queryForAll();
    }

    public void addToWatchlist(Movie movie) throws SQLException {
        dao.create(movieToEntity(movie));
    }

    public void removeFromWatchlist(Movie movie) throws SQLException {
        dao.delete(movieToEntity(movie));
    }

    private WatchlistEntity movieToEntity(Movie movie)
    {
        return new WatchlistEntity(movie.getId(), movie.getTitle(), movie.getDescription(), WatchlistEntity.genresToString(movie.getGenres()), movie.getReleaseYear(), movie.getImgUrl(), movie.getLengthInMinutes(), movie.getRating());
    }

}
