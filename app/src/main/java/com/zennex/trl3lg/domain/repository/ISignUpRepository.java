package com.zennex.trl3lg.domain.repository;

import com.zennex.trl3lg.domain.entities.Field;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by nikita on 12.06.17.
 */

public interface ISignUpRepository {

    Observable<List<Field>> getFieldsForSignUp(String moduleId);

    Observable<String> signUp(String moduleId, List<Field> fields);
}
