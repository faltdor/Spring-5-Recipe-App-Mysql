package com.faltdor.recipe.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.recipe.commands.UnitOfMeasureCommand;
import com.faltdor.recipe.domain.UnitOfMeasure;

public class UnitOfMeasureToUnitOfMeasureCommandTest {
	
	public static final String DESCRIPTION = "description";
	public static final Long LONG_VALUE = new Long(1L);
	
	private UnitOfMeasureToUnitOfMeasureCommand converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new UnitOfMeasureToUnitOfMeasureCommand();
	}
	
	@Test
	public void testNullObjectConvert() {
		
		assertNull(converter.convert(null));
	}
	
	@Test 
	public void testEmptyObject() {
		assertNotNull(converter.convert(new UnitOfMeasure()));
	}

	@Test
	public void testConvert() {
		//given
		UnitOfMeasure actual = new UnitOfMeasure();
		actual.setId(LONG_VALUE);
		actual.setDescription(DESCRIPTION);
		
		//When
		UnitOfMeasureCommand uomResult = converter.convert(actual);
		
		//Then
		assertNotNull(uomResult);
		assertEquals(uomResult.getId(), actual.getId());
		assertEquals(uomResult.getDescription(), actual.getDescription());
		
	}

}
