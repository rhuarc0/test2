package com.zennex.trl3lg.presentation.mapper;

import android.support.annotation.NonNull;

import com.zennex.trl3lg.data.entity.rest.response.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.data.mapper.Mapper;
import com.zennex.trl3lg.presentation.model.FieldModel;

import java.util.UUID;

import javax.inject.Inject;


/**
 * Created by nikita on 24.06.17.
 */

public class FieldModelMapper extends Mapper<GetFieldsForSignUpResponse.DataMemberField, FieldModel> {

    @Inject
    public FieldModelMapper() {
        super(FieldModel::new);
    }

    @NonNull
    @Override
    protected FieldModel transform(@NonNull GetFieldsForSignUpResponse.DataMemberField dataMemberField, FieldModel fieldModel) {
        fieldModel.setUuid(UUID.randomUUID().toString());
        fieldModel.setTitle(dataMemberField.getTitle());
        fieldModel.setType(dataMemberField.getType());
        fieldModel.setActive(dataMemberField.getActive().equals("1"));
        fieldModel.setAlias(dataMemberField.getAlias());
        fieldModel.setDefaultValue(dataMemberField.getDefaultValue());
        fieldModel.setEnumSet(dataMemberField.getEnumSet());
        fieldModel.setListOrder(dataMemberField.getListOrder());
        fieldModel.setRequired(dataMemberField.getRequired().equals("1"));
        fieldModel.setUserAlias(dataMemberField.getUserAlias());
        return fieldModel;
    }


}
