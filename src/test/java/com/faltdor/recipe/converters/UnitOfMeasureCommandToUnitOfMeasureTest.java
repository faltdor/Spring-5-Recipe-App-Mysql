package com.faltdor.recipe.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.recipe.commands.UnitOfMeasureCommand;
import com.faltdor.recipe.domain.UnitOfMeasure;

public class UnitOfMeasureCommandToUnitOfMeasureTest {

	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);
	
	UnitOfMeasureCommandToUnitOfMeasure converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureCommandToUnitOfMeasure();
	}
	
	@Test
	public void testNullParameter() {

		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		
		assertNotNull(converter.convert(new UnitOfMeasureCommand()));
	}

	@Test
	public void testConvert() {
		//Given
		UnitOfMeasureCommand actual = new UnitOfMeasureCommand();
		actual.setId(LONG_VALUE);
		actual.setDescription(DESCRIPTION);
		
		//when
		 UnitOfMeasure expected = converter.convert(actual );
		 
		 //Then
		 assertNotNull(expected);
		 assertEquals(expected.getId(), actual.getId());
		 assertEquals(expected.getDescription(), actual.getDescription());
		 
		
	}

}
