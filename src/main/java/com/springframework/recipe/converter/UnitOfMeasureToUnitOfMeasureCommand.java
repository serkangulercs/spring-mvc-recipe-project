package com.springframework.recipe.converter;

import com.springframework.recipe.command.UnitOfMeasureCommand;
import com.springframework.recipe.model.UnitOfMeasure;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * Created by jt on 6/21/17.
 */
@Component
public class UnitOfMeasureToUnitOfMeasureCommand implements Converter<UnitOfMeasure, UnitOfMeasureCommand> {

    @Synchronized
    @Nullable
    @Override
    public UnitOfMeasureCommand convert(UnitOfMeasure unitOfMeasure) {

        if (unitOfMeasure != null) {
            final UnitOfMeasureCommand uomCommand = new UnitOfMeasureCommand();
            uomCommand.setId(unitOfMeasure.getId());
            uomCommand.setDescription(unitOfMeasure.getDescription());
            return uomCommand;
        }
        return null;
    }
}
