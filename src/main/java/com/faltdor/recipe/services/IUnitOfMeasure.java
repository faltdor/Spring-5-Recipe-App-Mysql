package com.faltdor.recipe.services;

import java.util.Set;

import com.faltdor.recipe.commands.UnitOfMeasureCommand;

public interface IUnitOfMeasure {

	public Set<UnitOfMeasureCommand>  findAll();
}
