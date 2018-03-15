package com.zennex.trl3lg.presentation.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.rest.response.signup.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.domain.entities.Field;
import com.zennex.trl3lg.presentation.model.FieldModel;

import java.util.UUID;

import javax.inject.Inject;


public class FieldModelMapper extends Mapper<Field, FieldModel> {

    @Inject
    public FieldModelMapper() {
        super(FieldModel::new);
    }

    @NonNull
    @Override
    protected FieldModel transform(@NonNull Field field, FieldModel fieldModel) {
        fieldModel.setUuid(field.getUuid());
        fieldModel.setTitle(field.getTitle());
        fieldModel.setType(field.getType());
        fieldModel.setActive(field.isActive());
        fieldModel.setAlias(field.getAlias());
        fieldModel.setDefaultValue(field.getDefaultValue());
        fieldModel.setEnumSet(field.getEnumSet());
        fieldModel.setListOrder(field.getListOrder());
        fieldModel.setRequired(field.isRequired());
        fieldModel.setUserAlias(field.getUserAlias());
        return fieldModel;
    }

}
