package com.faltdor.recipe.converters;

import static org.junit.Assert.*;

import java.math.BigDecimal;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.recipe.commands.IngredientCommand;
import com.faltdor.recipe.commands.UnitOfMeasureCommand;
import com.faltdor.recipe.domain.Ingredient;
import com.faltdor.recipe.domain.Recipe;

public class IngredientCommandToIngredientTest {
	
	public static final Recipe RECIPE = new Recipe();
    public static final BigDecimal AMOUNT = new BigDecimal("1");
    public static final String DESCRIPTION = "Any description";
    public static final Long ID_VALUE = new Long(1L);
    public static final Long UOM_ID = new Long(2L);

	IngredientCommandToIngredient converter;
	
	@Before
	public void setUp() throws Exception {
		converter = new IngredientCommandToIngredient(new UnitOfMeasureCommandToUnitOfMeasure());
	}

	@Test
	public void testNullObject() {
		assertNull(converter.convert(null));
	}
	
	@Test
	public void testEmptyObject() {
		assertNotNull(converter.convert(new IngredientCommand()));
	}
	
	@Test
	public void testConvert() throws Exception {
		//Given
		IngredientCommand source = new IngredientCommand();
		source.setId(ID_VALUE);
		source.setAmount(AMOUNT);
		source.setDescription(DESCRIPTION);
		UnitOfMeasureCommand uom = new UnitOfMeasureCommand();
		uom.setId(UOM_ID);
		source.setMeasure(uom );
		
		//When
		Ingredient result = converter.convert(source);
		
		//Then
		assertNotNull(result);
        assertNotNull(result.getMeasure());
        assertEquals(ID_VALUE, result.getId());
        assertEquals(AMOUNT, result.getAmount());
        assertEquals(DESCRIPTION, result.getDescription());
        assertEquals(UOM_ID, result.getMeasure().getId());
	}
	
	@Test
	public void convertWithNullUOM() throws Exception {
		//Given
		IngredientCommand source = new IngredientCommand();
		source.setId(ID_VALUE);
		source.setAmount(AMOUNT);
		source.setDescription(DESCRIPTION);
		source.setMeasure(null);
		
		//When
		Ingredient result = converter.convert(source);
		
		//Then
		assertNotNull(result);
        assertNull(result.getMeasure());
        assertEquals(ID_VALUE, result.getId());
        assertEquals(AMOUNT, result.getAmount());
        assertEquals(DESCRIPTION, result.getDescription());
	}

}
