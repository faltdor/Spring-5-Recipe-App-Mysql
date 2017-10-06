package com.faltdor.recipe.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.recipe.commands.CategoryCommand;
import com.faltdor.recipe.domain.Category;

public class CategoryToCategoryCommandTest {
	
	private static final Long LONG_VALUE = 1l;
	private static final String DESCRIPTION = "Any description";
	
	CategoryToCategoryCommand converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new CategoryToCategoryCommand();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Category()));
	}
	
	@Test
	public void testConvert() {
		
		//Given
		Category source = new Category();
		source.setId(LONG_VALUE);
		source.setDescription(DESCRIPTION);
		
		//When
		CategoryCommand expected = converter.convert(source);
		assertNotNull(expected);
		assertEquals(expected.getId(), source.getId());
		assertEquals(expected.getDescription(), source.getDescription());
	}

}
