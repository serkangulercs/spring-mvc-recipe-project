package com.springframework.recipe.service;

import com.springframework.recipe.command.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(long recipeId, long ingredientId);
}
