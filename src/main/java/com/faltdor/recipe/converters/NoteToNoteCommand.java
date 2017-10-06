package com.faltdor.recipe.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.faltdor.recipe.commands.NoteCommand;
import com.faltdor.recipe.domain.Note;

@Component
public class NoteToNoteCommand implements Converter<Note, NoteCommand> {

	@Override
	public NoteCommand convert(Note source) {
		if(source == null) {return null;}
		
		final NoteCommand noteCommand = new NoteCommand();
		noteCommand.setId(source.getId());
		noteCommand.setNote(source.getNote());
		
		return noteCommand;
	}

		
}
