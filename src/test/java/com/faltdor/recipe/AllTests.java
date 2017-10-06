package com.faltdor.recipe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.faltdor.recipe.controllers.IndexControllerTest;
import com.faltdor.recipe.controllers.RecipeControllerTest;
import com.faltdor.recipe.converters.CategoryCommandToCategoryTest;
import com.faltdor.recipe.converters.CategoryToCategoryCommandTest;
import com.faltdor.recipe.converters.IngredientCommandToIngredientTest;
import com.faltdor.recipe.converters.IngredientToIngredientCommandTest;
import com.faltdor.recipe.converters.NoteToNoteCommandTest;
import com.faltdor.recipe.converters.NotesCommandToNotesTest;
import com.faltdor.recipe.converters.RecipeCommandToRecipeTest;
import com.faltdor.recipe.converters.RecipeToRecipeCommandTest;
import com.faltdor.recipe.converters.UnitOfMeasureCommandToUnitOfMeasureTest;
import com.faltdor.recipe.converters.UnitOfMeasureToUnitOfMeasureCommandTest;
import com.faltdor.recipe.domain.CategoryTest;
import com.faltdor.recipe.domain.UnitOfMeasureRepositoryTest_IT;
import com.faltdor.recipe.services.RecipeServiceIT;
import com.faltdor.recipe.services.RecipeServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({ Spring5RecipeAppApplicationTests.class ,
				IndexControllerTest.class,
				RecipeServiceImplTest.class,
				CategoryTest.class,
				UnitOfMeasureRepositoryTest_IT.class,
				RecipeControllerTest.class,
				UnitOfMeasureToUnitOfMeasureCommandTest.class,
				CategoryCommandToCategoryTest.class,
				CategoryToCategoryCommandTest.class,
				IngredientCommandToIngredientTest.class,
				IngredientToIngredientCommandTest.class,
				NotesCommandToNotesTest.class,
				NoteToNoteCommandTest.class,
				RecipeCommandToRecipeTest.class,
				RecipeToRecipeCommandTest.class,
				UnitOfMeasureCommandToUnitOfMeasureTest.class,
				UnitOfMeasureToUnitOfMeasureCommandTest.class,
				RecipeServiceIT.class
				
				})
public class AllTests {

}
