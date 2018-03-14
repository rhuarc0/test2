package com.zennex.trl3lg.data.mapper.dtomapper;

import android.support.annotation.NonNull;

import com.annimon.stream.function.Supplier;
import com.zennex.trl3lg.data.mapper.base.Mapper;
import com.zennex.trl3lg.data.rest.response.signup.GetFieldsForSignUpResponse;
import com.zennex.trl3lg.domain.entities.Field;

import java.util.UUID;

import javax.inject.Inject;


public class FieldDtoMapper extends Mapper<GetFieldsForSignUpResponse.DataMemberField, Field> {

    private static final String IS_ACTIVE = "1";
    private static final String IS_REQUIRED = "1";


    @Inject
    public FieldDtoMapper() {
        super(Field::new);
    }

    @NonNull
    @Override
    protected Field transform(@NonNull GetFieldsForSignUpResponse.DataMemberField source, Field destination) {
        destination.setUuid(UUID.randomUUID().toString());
        destination.setActive(source.getActive().equals(IS_ACTIVE));
        destination.setTitle(source.getTitle());
        destination.setAlias(source.getAlias());
        destination.setUserAlias(source.getUserAlias());
        destination.setType(source.getType());
        destination.setDefaultValue(source.getDefaultValue());
        destination.setRequired(source.getRequired().equals(IS_REQUIRED));
        destination.setListOrder(source.getListOrder());
        destination.setEnumSet(source.getEnumSet());

        return destination;
    }
}
