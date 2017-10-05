package com.faltdor.recipe.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.faltdor.recipe.domain.Category;

public interface ICategoryRepository extends CrudRepository<Category, Long> {

	Optional<Category> findByDescription(String description);
}
