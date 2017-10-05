package com.faltdor.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.faltdor.recipe.domain.UnitOfMeasure;

public interface IUnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

}
