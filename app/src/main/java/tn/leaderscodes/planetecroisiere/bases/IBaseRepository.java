package tn.leaderscodes.planetecroisiere.bases;

import java.util.List;

/**
 * Created by Sami on 22/12/2017.
 */

public interface IBaseRepository<T> {
    T add(T t);
    T update(T t);
    Integer delete(Integer id);
    List<T> getAll();
    T getById();
    T getByCondition(String where);
    List<T> getAllByCondition(String where);


}
