package tn.leaderscodes.planetecroisiere.bases;

import java.util.List;

/**
 * Created by Sami on 22/12/2017.
 */

public abstract class BaseRepository<T> implements IBaseRepository<T> {
    @Override
    public T add(T t) {
        return null;
    }

    @Override
    public T update(T t) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public List<T> getAll() {
        return null;
    }

    @Override
    public T getById() {
        return null;
    }

    @Override
    public T getByCondition(String where) {
        return null;
    }

    @Override
    public List<T> getAllByCondition(String where) {
        return null;
    }
}
