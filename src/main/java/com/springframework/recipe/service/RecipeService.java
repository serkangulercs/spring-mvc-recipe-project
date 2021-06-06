package com.springframework.recipe.service;

import com.springframework.recipe.command.RecipeCommand;
import com.springframework.recipe.model.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getRecipes();

    Recipe findById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
