package com.faltdor.recipe.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.faltdor.recipe.commands.UnitOfMeasureCommand;
import com.faltdor.recipe.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.faltdor.recipe.domain.UnitOfMeasure;
import com.faltdor.recipe.repositories.IUnitOfMeasureRepository;
import com.faltdor.recipe.services.impl.UnitOfMeasureService;

public class UnitOfMeasureServiceTest {
	
	
	@Mock
	IUnitOfMeasureRepository unitOfMeasureRepository;
	
	UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;
	
	UnitOfMeasureService unitOfMeasureService;
	
	
	
	@Before
	public void setUp() throws Exception {
		MockitoAnnotations.initMocks(this);
		unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();
		unitOfMeasureService = new UnitOfMeasureService(unitOfMeasureRepository,unitOfMeasureToUnitOfMeasureCommand);
	}

	@Test
	public void testFindAll() {
		UnitOfMeasure uom1 = new UnitOfMeasure();
		uom1.setId(10L);
		
		UnitOfMeasure uom2 = new UnitOfMeasure();
		uom1.setId(11L);
		
		UnitOfMeasure uom3 = new UnitOfMeasure();
		uom1.setId(12L);
		
		Set<UnitOfMeasure>  list = new HashSet<>();
		list.add(uom1);
		list.add(uom2);
		list.add(uom3);
		
		
		when(unitOfMeasureRepository.findAll()).thenReturn(list);
		
		
		//When
		Set<UnitOfMeasureCommand> listUnitOfMeasure = unitOfMeasureService.findAll();
		
		//Then
		assertNotNull(listUnitOfMeasure);
		assertTrue(!listUnitOfMeasure.isEmpty());
		assertTrue(listUnitOfMeasure.size() == 2);
		
		verify(unitOfMeasureRepository,times(1)).findAll();
		
	}

}
