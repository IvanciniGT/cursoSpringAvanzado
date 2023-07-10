package com.curso.animalitos.servicio.testdoubles;

import com.curso.animalitos.entidades.Animalito;
import com.curso.animalitos.entidades.RepositorioAnimalitos;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;
import java.util.Optional;

@Profile("unit-testing-con-fake")
public class AnimalitosRepositorioFake implements RepositorioAnimalitos {
    @Override
    public List<Animalito> findAll() {
        return null;
    }

    @Override
    public List<Animalito> findAll(Sort sort) {
        return null;
    }

    @Override
    public Page<Animalito> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public List<Animalito> findAllById(Iterable<Long> iterable) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(Animalito animalito) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> iterable) {

    }

    @Override
    public void deleteAll(Iterable<? extends Animalito> iterable) {

    }

    @Override
    public void deleteAll() {

    }


    private Long id=0L;
    @Override
    public <S extends Animalito> S save(S s) {
        s.setId(++id);
        return s;
    }

    @Override
    public <S extends Animalito> List<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Animalito> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public void flush() {

    }

    @Override
    public <S extends Animalito> S saveAndFlush(S s) {
        return null;
    }

    @Override
    public <S extends Animalito> List<S> saveAllAndFlush(Iterable<S> iterable) {
        return null;
    }

    @Override
    public void deleteAllInBatch(Iterable<Animalito> iterable) {

    }

    @Override
    public void deleteAllByIdInBatch(Iterable<Long> iterable) {

    }

    @Override
    public void deleteAllInBatch() {

    }

    @Override
    public Animalito getOne(Long aLong) {
        return null;
    }

    @Override
    public Animalito getById(Long aLong) {
        return null;
    }

    @Override
    public <S extends Animalito> Optional<S> findOne(Example<S> example) {
        return Optional.empty();
    }

    @Override
    public <S extends Animalito> List<S> findAll(Example<S> example) {
        return null;
    }

    @Override
    public <S extends Animalito> List<S> findAll(Example<S> example, Sort sort) {
        return null;
    }

    @Override
    public <S extends Animalito> Page<S> findAll(Example<S> example, Pageable pageable) {
        return null;
    }

    @Override
    public <S extends Animalito> long count(Example<S> example) {
        return 0;
    }

    @Override
    public <S extends Animalito> boolean exists(Example<S> example) {
        return false;
    }
}
