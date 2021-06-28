package com.springframework.recipe.service;

import com.springframework.recipe.converter.RecipeCommandToRecipe;
import com.springframework.recipe.converter.RecipeToRecipeCommand;
import com.springframework.recipe.exception.NotFoundException;
import com.springframework.recipe.model.Recipe;
import com.springframework.recipe.repository.RecipeRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RecipeServiceImplTest {

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;
    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;
    private RecipeServiceImpl recipeService;
    private AutoCloseable closeable;
    @Mock
    private RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        recipeService = new RecipeServiceImpl(recipeRepository, recipeCommandToRecipe, recipeToRecipeCommand);
    }

    @AfterEach
    public void close() throws Exception {
        closeable.close();
    }

    @Test
    public void getRecipes() {
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();
        assertEquals(recipes.size(), 1);
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    public void getRecipesById() throws Exception {
        Recipe recipe = new Recipe();
        recipe.setId(1L);
        Optional<Recipe> recipeOptional = Optional.of(recipe);

        when(recipeRepository.findById(any())).thenReturn(recipeOptional);

        Recipe returnedRecipe = recipeService.findById(1L);

        assertNotNull(returnedRecipe);
        verify(recipeRepository, times(1)).findById(any());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    public void getRecipeByIdNotFound() throws Exception {

        Optional<Recipe> recipeOptional = Optional.empty();

        when(recipeRepository.findById(anyLong())).thenReturn(recipeOptional);

        Exception exception = assertThrows(NotFoundException.class, () -> {
            recipeService.findById(1L);
        });

        String expectedMessage = "Recipe Not Found";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void getRecipeById_throws_NumberFormatException_when_id_is_not_a_number() throws Exception {
        assertThrows(NumberFormatException.class, () -> {
            recipeService.findById(Long.valueOf("asd"));
        });
    }

    @Test
    public void deleteById() throws Exception {
        //given
        Long idToDelete = Long.valueOf(2L);

        //when
        recipeService.deleteById(idToDelete);

        //no 'when', since method has void return type

        //then
        verify(recipeRepository, times(1)).deleteById(anyLong());
    }
}