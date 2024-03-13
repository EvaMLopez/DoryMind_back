package dev.eva.dorymind.generics;

import java.util.List;

public interface IGenericService <T>  {
   
    List<T> findAll();

    T findById(Long id);

    T save(T entity);

    T update(T entity);
    
    void deleteById(Long id);

    void delete(T entity);

    void deleteAll();

}