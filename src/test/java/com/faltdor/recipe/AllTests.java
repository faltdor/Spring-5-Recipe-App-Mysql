package com.faltdor.recipe;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import com.faltdor.recipe.controllers.IndexControllerTest;
import com.faltdor.recipe.controllers.RecipeControllerTest;
import com.faltdor.recipe.domain.CategoryTest;
import com.faltdor.recipe.domain.UnitOfMeasureRepositoryTest_IT;
import com.faltdor.recipe.services.RecipeServiceImplTest;

@RunWith(Suite.class)
@SuiteClasses({ Spring5RecipeAppApplicationTests.class ,
				IndexControllerTest.class,
				RecipeServiceImplTest.class,
				CategoryTest.class,
				UnitOfMeasureRepositoryTest_IT.class,
				RecipeControllerTest.class})
public class AllTests {

}
