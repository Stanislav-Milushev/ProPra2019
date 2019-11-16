package propra.grpproj.quiz.repositories;

import java.util.Optional;

/**
 * <p>
 * Adapter class for concrete crud-repository implementations.
 * <p>
 * Throws UnsupportedOperationException for each method that shall not be
 * adapted.
 *
 * @param <T> Type of the entity to be managed by this repository
 * @param <ID> Type of the id (primary key) of the managed entity
 */
public abstract class CrudRepositoryAdapter<T, ID> implements CrudRepository<T, ID>
{
    // @formatter:off

    @Override public long count() { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public void delete(T entity) { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public void deleteAll() { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public void deleteAll(Iterable<? extends T> entities) { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public void deleteById(ID id) { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public boolean existsById(ID id) { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public Iterable<T> findAll() { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public Iterable<T> findAllById(Iterable<ID> ids) { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public Optional<T> findById(ID id) { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public <S extends T> S save(S entity) { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    @Override public <S extends T> Iterable<S> saveAll(Iterable<S> entities) { throw new UnsupportedOperationException("Not implemented. Super-Adapter called."); }

    // @formatter:on
}
