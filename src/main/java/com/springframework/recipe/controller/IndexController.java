package com.springframework.recipe.controller;

import com.springframework.recipe.model.Category;
import com.springframework.recipe.model.UnitOfMeasure;
import com.springframework.recipe.repository.CategoryRepository;
import com.springframework.recipe.repository.UnitOfMeasureRepository;
import com.springframework.recipe.service.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class IndexController {

    private final RecipeService recipeService;

    public IndexController(RecipeService recipeService) {
        this.recipeService = recipeService;
    }

    @RequestMapping({"", "/", "/index"})
    public String getIndexPage(Model model) {
        model.addAttribute("recipes", recipeService.getRecipes());
        return "index";
    }
}
