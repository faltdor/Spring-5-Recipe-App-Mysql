package com.faltdor.recipe.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.recipe.commands.CategoryCommand;
import com.faltdor.recipe.commands.NoteCommand;
import com.faltdor.recipe.domain.Category;
import com.faltdor.recipe.domain.Note;

public class CategoryCommandToCategoryTest {
	
	private static final Long LONG_VALUE = 1l;
	private static final String DESCRIPTION = "Any description";
	
	CategoryCommandToCategory converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new CategoryCommandToCategory();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new CategoryCommand()));
	}
	
	@Test
	public void testConvert() {
		
		//Given
		CategoryCommand source = new CategoryCommand();
		source.setId(LONG_VALUE);
		source.setDescription(DESCRIPTION);
		
		//When
		Category expected = converter.convert(source);
		assertNotNull(expected);
		assertEquals(expected.getId(), source.getId());
		assertEquals(expected.getDescription(), source.getDescription());
	}

}
