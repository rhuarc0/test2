package com.zennex.trl3lg.presentation.module.signup.view;

import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.TextInputEditText;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.annimon.stream.Stream;
import com.zennex.trl3lg.domain.usecases.common.UseCase;
import com.zennex.trl3lg.presentation.common.di.rxschedulers.RxSchedulerModule;
import com.zennex.trl3lg.presentation.model.FieldModel;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.zennex.trl3lg.presentation.model.FieldModel.TYPE_CHECKBOX;
import static com.zennex.trl3lg.presentation.model.FieldModel.TYPE_DECIMAL;
import static com.zennex.trl3lg.presentation.model.FieldModel.TYPE_ENUM;
import static com.zennex.trl3lg.presentation.model.FieldModel.TYPE_INTEGER;
import static com.zennex.trl3lg.presentation.model.FieldModel.TYPE_TEXT;
import static com.zennex.trl3lg.presentation.model.FieldModel.TYPE_VARCHAR;

/**
 * Created by nikita on 07.07.17.
 */

public class ViewCreator extends UseCase<Map<FieldModel, View>, List<FieldModel>> {

    public static final String TAG = "ViewCreator";

    @NonNull
    private final AppCompatActivity mActivity;


    @Inject
    public ViewCreator(@NonNull AppCompatActivity activity,
                       @Named(RxSchedulerModule.COMPUTATION) @NonNull Scheduler subscriberScheduler,
                       @Named(RxSchedulerModule.MAIN) @NonNull Scheduler observerScheduler) {
        super(subscriberScheduler, observerScheduler);
        mActivity = activity;
    }

    @Override
    protected Observable<Map<FieldModel, View>> buildObservable(List<FieldModel> fieldModels) {
        return Observable.fromCallable(() -> {
            LinkedHashMap<FieldModel, View> map = new LinkedHashMap<>();
            Stream.of(fieldModels).forEach(fieldModel -> map.put(fieldModel, createViewByFieldModel(fieldModel)));
            return map;
        });
    }

    @NonNull
    private View createViewByFieldModel(@NonNull FieldModel fieldModel) {
        View view;
        //Log.d(TAG, "createViewByFieldModel: field model =  " + fieldModel.toString());

        if (isTypeVarcharPassword(fieldModel)) view = createEditTextTypePassword(fieldModel);
        else if (isTypeVarcharText(fieldModel)) view = createEditTextTypeText(fieldModel);
        else if (isTypeCheckBox(fieldModel)) view = createCheckBox(fieldModel);
        else if (isTypeSpinner(fieldModel)) view = createSpinner(fieldModel);
        else if (isTypeText(fieldModel)) view = createTextView(fieldModel);
        else if (isTypeInteger(fieldModel)) view = createEditTextTypeInteger(fieldModel);
        else view = createEditTextTypeDecimal(fieldModel);
        return view;
    }

    private boolean isTypeVarcharPassword(FieldModel fieldModel) {
        return fieldModel.getType().equals(TYPE_VARCHAR) && fieldModel.getAlias().equals("text0");
    }

    private boolean isTypeVarcharText(FieldModel fieldModel) {
        return fieldModel.getType().equals(TYPE_VARCHAR) && !fieldModel.getAlias().equals("text0");
    }

    private boolean isTypeCheckBox(FieldModel fieldModel) {
        return fieldModel.getType().equals(TYPE_CHECKBOX);
    }

    private boolean isTypeSpinner(FieldModel fieldModel) {
        return fieldModel.getType().equals(TYPE_ENUM);
    }

    private boolean isTypeText(FieldModel fieldModel) {
        return fieldModel.getType().equals(TYPE_TEXT);
    }

    private boolean isTypeInteger(FieldModel fieldModel) {
        return fieldModel.getType().equals(TYPE_INTEGER);
    }

    private boolean isTypeDecimal(FieldModel fieldModel) {
        return fieldModel.getType().equals(TYPE_DECIMAL);
    }


    private TextInputLayout createNewTextInputLayout() {
        TextInputLayout textInputLayout = new TextInputLayout(mActivity);
        textInputLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));
        return textInputLayout;
    }

    private TextInputEditText createNewBaseTextInputEditText(FieldModel fieldModel) {
        TextInputEditText textInputEditText = new TextInputEditText(mActivity);
        textInputEditText.setHint(getTitleByFieldModel(fieldModel));
        textInputEditText.setSingleLine(false);
        textInputEditText.setId(View.generateViewId());
        return textInputEditText;
    }

    private TextInputLayout createEditTextTypeText(FieldModel fieldModel) {
        TextInputLayout textInputLayout = createNewTextInputLayout();
        TextInputEditText textInputEditText = createNewBaseTextInputEditText(fieldModel);
        textInputLayout.addView(textInputEditText);
        return textInputLayout;
    }

    private TextInputLayout createEditTextTypePassword(FieldModel fieldModel) {
        TextInputLayout textInputLayout = createNewTextInputLayout();
        textInputLayout.setPasswordVisibilityToggleEnabled(true);
        TextInputEditText textInputEditText = createNewBaseTextInputEditText(fieldModel);
        textInputEditText.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        textInputLayout.addView(textInputEditText);
        return textInputLayout;
    }

    private TextInputLayout createEditTextTypeInteger(FieldModel fieldModel) {
        TextInputLayout textInputLayout = createNewTextInputLayout();
        TextInputEditText textInputEditText = createNewBaseTextInputEditText(fieldModel);
        textInputLayout.addView(textInputEditText);
        return textInputLayout;
    }

    private TextInputLayout createEditTextTypeDecimal(FieldModel fieldModel) {
        TextInputLayout textInputLayout = createNewTextInputLayout();
        TextInputEditText textInputEditText = createNewBaseTextInputEditText(fieldModel);
        textInputLayout.addView(textInputEditText);
        return textInputLayout;
    }


    private CheckBox createCheckBox(FieldModel fieldModel) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        CheckBox checkBox = new CheckBox(mActivity);
        checkBox.setText(getTitleByFieldModel(fieldModel));
        checkBox.setLayoutParams(params);
        return checkBox;
    }

    private Spinner createSpinner(FieldModel fieldModel) {
        Spinner spinner;
        // Need ui thread on versions 21 and 22, only for create new spinner (maybe bug)
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1) {
            spinner = new Spinner(mActivity);
        } else {
            spinner = Observable.fromCallable(() -> new Spinner(mActivity))
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(Schedulers.trampoline())
                    .blockingSingle();
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mActivity, android.R.layout.simple_spinner_item, fieldModel.getEnumSet());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        return spinner;
    }

    private TextView createTextView(FieldModel fieldModel) {
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        TextView textView = new TextView(mActivity);
        textView.setText(getTitleByFieldModel(fieldModel));
        textView.setLayoutParams(params);
        return textView;
    }

    private String getTitleByFieldModel(FieldModel fieldModel) {
        return fieldModel.isRequired() ? fieldModel.getTitle().concat("*") : fieldModel.getTitle();
    }

}
