package com.springframework.recipe.service;

import com.springframework.recipe.command.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {

    Set<UnitOfMeasureCommand> findAll();
}
