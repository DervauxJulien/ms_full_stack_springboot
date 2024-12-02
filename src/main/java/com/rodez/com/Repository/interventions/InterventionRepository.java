//package com.rodez.com.Repository.interventions;

import com.rodez.com.Entity.Intervention;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.data.repository.query.Param;
//import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;
//@Repository
/*public class InterventionRepository implements InterventionRepositoryInterface {


    @PersistenceContext
    private EntityManager entityManager;

    // code de récupération depuis dba des interventins de user id_user
    //@Query(value = "SELECT * FROM INTERVENTION  WHERE id_user = ?1", nativeQuery = true)
    //@Override
    //public Optional<List<Intervention>> getMyRequest(@Param("id") Integer id_user){


   // }


}





















    @Override
    public void flush() {

    }

    @Override
    public <S extends Intervention> S saveAndFlush(S entity) {
        return null;
    }

    @Override
    public <S extends Intervention> List<S> saveAllAndFlush(Iterable<S> entities) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Intervention> entities) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Integer> integers) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Intervention getOne(Integer integer) {
        return null;
    }

    @Override
    public Intervention getById(Integer integer) {
        return null;
    }

    @Override
    public Intervention getReferenceById(Integer integer) {
        return null;
    }

    @Override
    public <S extends Intervention> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Intervention> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Intervention> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Intervention> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Intervention> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Intervention> boolean exists(Example<S> example) {
        return false;
    }

    @Override
    public <S extends Intervention, R> R findBy(Example<S> example, Function<FluentQuery.FetchableFluentQuery<S>, R> queryFunction) {
        return null;
    }

    @Override
    public <S extends Intervention> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Intervention> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Intervention> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Integer integer) {
        return false;
    }

    @Override
    public List<Intervention> findAll() {
        return null;
    }

    @Override
    public List<Intervention> findAllById(Iterable<Integer> integers) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Integer integer) {

    }

    @Override
    public void delete(Intervention entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Integer> integers) {

    }

    @Override
    public void deleteAll(Iterable<? extends Intervention> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public List<Intervention> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Intervention> findAll(Pageable pageable) {
        return null;
    }

}

 */
