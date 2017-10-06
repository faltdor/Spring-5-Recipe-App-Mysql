package com.faltdor.recipe.converters;

import javax.validation.constraints.Null;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.faltdor.recipe.commands.UnitOfMeasureCommand;
import com.faltdor.recipe.domain.UnitOfMeasure;

import lombok.Synchronized;

@Component
public class UnitOfMeasureCommandToUnitOfMeasure implements Converter<UnitOfMeasureCommand, UnitOfMeasure> {

	@Override
	@Null
	@Synchronized
	public UnitOfMeasure convert(UnitOfMeasureCommand source) {
		if(source == null) {return null;}
		
		final UnitOfMeasure unitOfMeasure = new UnitOfMeasure();
		unitOfMeasure.setId(source.getId());
		unitOfMeasure.setDescription(source.getDescription());
		
		return unitOfMeasure;
	}

}
