package com.faltdor.recipe.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.recipe.commands.NoteCommand;
import com.faltdor.recipe.domain.Note;

public class NoteToNoteCommandTest {
	
	
	private static final Long LONG_VALUE = 1l;
	private static final String DESCRIPTION = "Any description";
	
	NoteToNoteCommand converter;

	@Before
	public void setUp() throws Exception {
		converter = new NoteToNoteCommand();
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new Note()));
	}
	
	@Test
	public void testConverter() {
		//Given
		Note source = new Note();
		source.setId(LONG_VALUE);
		source.setNote(DESCRIPTION);
		
		//when
		 NoteCommand expected = converter.convert(source);
		 
		 //Then
		 assertNotNull(expected);
		 assertEquals(expected.getId(), source.getId());
		 assertEquals(expected.getNote(), source.getNote());
		 
	}
	

}
