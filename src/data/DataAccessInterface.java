package data;

import java.sql.SQLException;
import java.util.List;

public interface DataAccessInterface<T> {
	public T create(T o) throws SQLException;
	public T update(T o);
	public void delete(T o);
	public T get(T o);
	public List<T> getAll();
}
