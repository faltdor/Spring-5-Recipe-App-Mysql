package com.faltdor.recipe.converters;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.recipe.commands.NoteCommand;
import com.faltdor.recipe.domain.Note;

public class NotesCommandToNotesTest {
	
	private static final Long LONG_VALUE = 1l;
	private static final String DESCRIPTION = "Any description";
	
	NotesCommandToNotes converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new NotesCommandToNotes();
	}
	
	@Test
	public void testNullParameter() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new NoteCommand()));
	}
	
	@Test
	public void testConvert() {
		//Given
		NoteCommand source = new NoteCommand();
		source.setId(LONG_VALUE);
		source.setNote(DESCRIPTION);
		
		//When
		Note expected = converter.convert(source);
		assertNotNull(expected);
		assertEquals(expected.getId(), source.getId());
		assertEquals(expected.getNote(), source.getNote());
	
	}

}
