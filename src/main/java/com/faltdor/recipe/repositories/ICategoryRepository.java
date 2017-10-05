package com.faltdor.recipe.repositories;

import org.springframework.data.repository.CrudRepository;

import com.faltdor.recipe.domain.Category;

public interface ICategoryRepository extends CrudRepository<Category, Long> {

}
