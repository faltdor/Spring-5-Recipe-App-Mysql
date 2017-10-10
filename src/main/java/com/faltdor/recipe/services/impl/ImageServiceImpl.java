package com.faltdor.recipe.services.impl;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.faltdor.recipe.domain.Recipe;
import com.faltdor.recipe.repositories.IRecipeRepository;
import com.faltdor.recipe.services.IImageService;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ImageServiceImpl implements IImageService {

	IRecipeRepository recipeRepository;

	public ImageServiceImpl(IRecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Transactional
	public void saveImageFile(Long recipeId, MultipartFile file) {

		try {
			Recipe recipe = recipeRepository.findById(recipeId).get();

			Byte[] byteObjects = new Byte[file.getBytes().length];

			int i = 0;

			for (byte b : file.getBytes()) {
				byteObjects[i++] = b;
			}

			recipe.setImage(byteObjects);

			recipeRepository.save(recipe);
		} catch (IOException e) {
			// todo handle better
			log.error("Error occurred", e);

			e.printStackTrace();
		}

	}

}
