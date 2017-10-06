package com.faltdor.recipe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.faltdor.recipe.controllers.IndexControllerTest;
import com.faltdor.recipe.domain.CategoryTest;
import com.faltdor.recipe.services.RecipeServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({ Spring5RecipeAppApplicationTests.class ,
				IndexControllerTest.class,
				RecipeServiceImplTest.class,
				CategoryTest.class})
public class AllTests {

}
