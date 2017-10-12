package com.faltdor.recipe.services.impl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;

import com.faltdor.recipe.commands.UnitOfMeasureCommand;
import com.faltdor.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.faltdor.recipe.repositories.IUnitOfMeasureRepository;
import com.faltdor.recipe.services.IUnitOfMeasure;

@Service
public class UnitOfMeasureService implements IUnitOfMeasure {
	
	private final  IUnitOfMeasureRepository uomReposiroty;
	private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	public UnitOfMeasureService(IUnitOfMeasureRepository uomReposiroty,
								UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {
		this.uomReposiroty = uomReposiroty;
		this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
	}



	@Override
	public Set<UnitOfMeasureCommand> findAll() {
		
		
		return  StreamSupport.stream(uomReposiroty.findAll()
							 .spliterator(),false)
							 .map(unitOfMeasureToUnitOfMeasureCommand::convert)
							 .collect(Collectors.toSet());
							 
	}

}
