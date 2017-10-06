package com.faltdor.recipe.domain;

import static org.junit.Assert.*;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.faltdor.recipe.repositories.IUnitOfMeasureRepository;

@RunWith(SpringRunner.class)
@DataJpaTest
public class UnitOfMeasureRepositoryTest_IT {

	@Autowired
	IUnitOfMeasureRepository unitOfMeasureRepository;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findByDescriptionTest() {
		
		String unitOfMeasure = "Teaspoon";
		
		Optional<UnitOfMeasure> unitOfMesuOptional = unitOfMeasureRepository.findByDescription(unitOfMeasure);
		
		assertEquals(unitOfMeasure,unitOfMesuOptional.get().getDescription());
		
	}
	
	@Test
	public void findByDescriptionTesUndefined() {
		
		String unitOfMeasure = "Te";
		
		Optional<UnitOfMeasure> unitOfMesuOptional = unitOfMeasureRepository.findByDescription(unitOfMeasure);
		
		assertEquals(Optional.empty(),unitOfMesuOptional.get().getDescription());
		
	}

}


