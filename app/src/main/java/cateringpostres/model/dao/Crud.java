package cateringpostres.model.dao;

import java.util.List;

public interface Crud<T> {
    public List<T> getList();

    public T getById(int id);

    public boolean add(T objectT);

    public boolean update(T objectT);
    
    public boolean delete(T objectT);
}
