package com.zennex.trl3lg.presentation.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.domain.entities.Field;
import com.zennex.trl3lg.presentation.model.FieldModel;

import javax.inject.Inject;


public class FieldModelToModelMapper extends Mapper<FieldModel, Field> {

    @Inject
    public FieldModelToModelMapper() {
        super(Field::new);
    }

    @NonNull
    @Override
    protected Field transform(@NonNull FieldModel fieldModel, Field field) {
        field.setUuid(fieldModel.getUuid());
        field.setTitle(fieldModel.getTitle());
        field.setType(fieldModel.getType());
        field.setActive(fieldModel.isActive());
        field.setAlias(fieldModel.getAlias());
        field.setDefaultValue(fieldModel.getDefaultValue());
        field.setEnumSet(fieldModel.getEnumSet());
        field.setListOrder(fieldModel.getListOrder());
        field.setRequired(fieldModel.isRequired());
        field.setUserAlias(fieldModel.getUserAlias());
        field.setValue(fieldModel.getValue());
        field.setErrorEnable(fieldModel.isErrorEnable());
        field.setEnable(fieldModel.isEnable());
        return field;
    }

}
