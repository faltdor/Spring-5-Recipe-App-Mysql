package com.faltdor.recipe.commands;

import java.util.HashSet;
import java.util.Set;

import com.faltdor.recipe.enums.Difficulty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
	
	private Long id;
	private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;
    private String directions;
    private Byte image;
    private Difficulty difficulty;
    private NoteCommand note;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();
    
    
    

}
