package com.faltdor.recipe.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.faltdor.recipe.services.impl.ImageServiceImpl;
import com.faltdor.recipe.services.impl.RecipeServiceImpl;

@Controller
public class ImageController {
	
	private  final ImageServiceImpl imageService;
	private final RecipeServiceImpl recipeService;

	public ImageController(ImageServiceImpl imageService,RecipeServiceImpl recipeService) {
		this.imageService = imageService;
		this.recipeService = recipeService;
	}
	
	@GetMapping("recipe/{id}/image")
    public String showUploadForm(@PathVariable String id, Model model){
        model.addAttribute("recipe", recipeService.findCommandById(Long.valueOf(id)));

        return "recipe/imageuploadform";
    }

    @PostMapping("recipe/{id}/image")
    public String handleImagePost(@PathVariable String id, @RequestParam("imagefile") MultipartFile file){

        imageService.saveImageFile(Long.valueOf(id), file);

        return "redirect:/recipe/" + id + "/show";
}
	
	
	
}
