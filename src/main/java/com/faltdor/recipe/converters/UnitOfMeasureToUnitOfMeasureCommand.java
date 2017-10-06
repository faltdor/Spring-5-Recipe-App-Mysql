package com.faltdor.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.faltdor.recipe.commands.UnitOfMeasureCommand;
import com.faltdor.recipe.domain.UnitOfMeasure;


@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

	@Override
	public UnitOfMeasureCommand convert(UnitOfMeasure source) {
		if(source ==null) { return null;}
		
		final UnitOfMeasureCommand measureCommand = new UnitOfMeasureCommand();
		measureCommand.setId(source.getId());
		measureCommand.setDescription(source.getDescription());
			
		return measureCommand;
	}

}
