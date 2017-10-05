package com.faltdor.recipe.controller;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.faltdor.recipe.domain.Category;
import com.faltdor.recipe.domain.UnitOfMeasure;
import com.faltdor.recipe.repositories.ICategoryRepository;
import com.faltdor.recipe.repositories.IUnitOfMeasureRepository;

@Controller
public class IndexController {

	private ICategoryRepository categoryRepository;
	private IUnitOfMeasureRepository unitOfMeasureRepository;
	
	
	
	public IndexController(ICategoryRepository categoryRepository, IUnitOfMeasureRepository unitOfMeasureRepository) {
		this.categoryRepository = categoryRepository;
		this.unitOfMeasureRepository = unitOfMeasureRepository;
	}



	@RequestMapping({"","/","/index"})
	public String getIndexPage() {
		
		Optional<Category> categoryOptional = categoryRepository.findByDescription("Italian");
		Optional<UnitOfMeasure> unitOfMeasureOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
		
		System.out.println("Cat id is :"+ categoryOptional.get().getId());
		System.out.println("UOM id is :"+ unitOfMeasureOptional.get().getId());
		
		return "index";
	}
	
}
