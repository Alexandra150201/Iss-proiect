package theater.persistence;


import theater.model.Entity;

import java.io.IOException;

/**
 * CRUD operations com.example.javafx.repository interface
 * @param <ID> - type E must have an attribute of type ID
 * @param <E> -  type of entities saved in com.example.javafx.repository
 */

public interface Repository<ID, E extends Entity<ID>> {

    int size();

    /**
     *
     * @param id -the id of the entity to be returned
     *           id must not be null
     * @return the entity with the specified id
     *          or null - if there is no entity with the given id
     * @throws IllegalArgumentException
     *                  if id is null.
     */
    E findOne(ID id);

    /**
     *
     * @return all entities
     */
    Iterable<E> findAll();

    /**
     *
     * @param entity
     *         entity must be not null
     * @throws IllegalArgumentException
     *             if the given entity is null.     *
     */
    void save(E entity);


    /**
     *  removes the entity with the specified id
     * @param id
     *      id must be not null
     * @throws IllegalArgumentException
     *                   if the given id is null.
     */
    void delete(E entity) throws IOException;

    /**
     *
     * @param entity
     *          entity must not be null
     * @return null - if the entity is updated,
     *                otherwise  returns the entity  - (e.g id does not exist).
     * @throws IllegalArgumentException
     *             if the given entity is null.
     */
    E update(E entity);

}
