package com.faltdor.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import com.faltdor.recipe.commands.NoteCommand;
import com.faltdor.recipe.domain.Note;

import lombok.Synchronized;

@Component
public class NotesCommandToNotes implements Converter<NoteCommand, Note> {

	@Override
	@Synchronized
	@Nullable
	public Note convert(NoteCommand source) {
		if(source ==null) {return null;}
		
		final Note note = new Note();
		note.setId(source.getId());
		note.setNote(source.getNote());
		
		return note;
		
	}

}
