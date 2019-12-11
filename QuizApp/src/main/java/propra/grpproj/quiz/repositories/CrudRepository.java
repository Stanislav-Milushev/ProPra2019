package propra.grpproj.quiz.repositories;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * <p>
 * CRUD Repository interface to be implemented by each concrete repository.
 * 
 * 
 * @see CrudRepositoryAdapter
 *
 * @param <T> Type of the entity to be managed by this repository
 * @param <ID> Type of the id (primary key) of the managed entity
 * 
 * @author Daniel
 * 
 */
public interface CrudRepository<T, ID>
{

    // ========================================================================
    // Utilities
    // ========================================================================

    /**
     * <p>
     * Utility helper method to convert a Iterable to a List
     * <p>
     * NOTICE: The concrete implementation must not return a Set nor Map when using
     * this method.
     */
    public static <T> List<T> convertToList(Iterable<T> iterable)
    {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toList());
    }

    /**
     * <p>
     * Utility helper method to convert a Iterable to a Set
     * <p>
     * NOTICE: The concrete implementation must not return a List nor Map when using
     * this method.
     */
    public static <T> Set<T> convertToSet(Iterable<T> iterable)
    {
        return StreamSupport.stream(iterable.spliterator(), false).collect(Collectors.toSet());
    }

    // ========================================================================
    // actual interface to be implemented
    // ========================================================================

    /**
     * Returns the number of entities available.
     * 
     * @return the number of entities available.
     */
    long count();

    /**
     * Deletes a given entity.
     * 
     * @param entity the entity to be delete - must not be null
     */
    void delete(T entity);

    /**
     * Deletes all entities managed by the repository.
     */
    void deleteAll();

    /**
     * Deletes the given entities.
     * 
     * @param entities an iterable containing all entities to be deleted - must not
     * be null. Must not contain null elements.
     */
    void deleteAll(Iterable<? extends T> entities);

    /**
     * Deletes the entity with the given id.
     * 
     * @param id the id of the entity to be deleted - must not be null.
     */
    void deleteById(ID id);

    /**
     * Returns whether an entity with the given id exists.
     * 
     * @param id the id of the entity to perform the check - must not be null.
     * @return true if an entity with the given id exists, false otherwise.
     */
    boolean existsById(ID id);

    /**
     * Returns all instances of the type.
     * 
     * @return all entities.
     */
    Iterable<T> findAll();

    /**
     * Returns all instances of the type T with the given IDs.
     * 
     * @param ids must not be null nor contain any null values.
     * @return an iterable that is guaranteed to be not null. The size can be equal
     * or less than the number of given ids.
     */
    Iterable<T> findAllById(Iterable<ID> ids);

    /**
     * Retrieves an entity by its id.
     * 
     * @param id the id of the entity to be retrieved - must not be null.
     * @return the entity with the given id wrapped in an optional or
     * {@link Optional#empty()} if none found.
     */
    Optional<T> findById(ID id);

    /**
     * <p>
     * Saves or updates a given entity.
     * <p>
     * If the given entity exists it gets updated, saved otherwise.
     * 
     * @param <S> The type of the entity that extends the base type T.
     * @param entity the entity to be saved - must not be null.
     * @return the saved entity; will never be null.
     */
    <S extends T> S save(S entity);

    /**
     * Saves or updates all given entities.
     * 
     * @param <S> The type of the entity that extends the base type T.
     * @param entities must not be null nor must it contain null.
     * @return the saved entities; will never be null. The returned Iterable will
     * have the same size as the Iterable passed as an argument.
     */
    <S extends T> Iterable<S> saveAll(Iterable<S> entities);

}
