package com.faltdor.recipe.domain;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.faltdor.recipe.domain.Category;


public class CategoryTest {

	Category category;
	
	@Before
	public void setUp() {
		category = new Category();
	}
	
	@Test
	public void getId() {
		category.setId(4L);
		assertNotNull(category.getId());
	}

}
