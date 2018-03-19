package com.zennex.trl3lg.presentation.module.main.submodule.user.view;

import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.domain.entities.Member;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.user.UserScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.UserPresenter;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by nikita on 22.07.17.
 */
@Layout(R.layout.frg_user_layout)
public class UserFragmentView extends UserScreenContract.AbstractUserView {

    public static final String TAG = "UserFragmentView";

    @BindView(R.id.frg_user_edt_name)
    EditText edtName;

    @BindView(R.id.frg_user_edt_email)
    EditText edtEmail;

    @BindView(R.id.frg_user_edt_password)
    EditText edtPassword;

    @BindView(R.id.frg_user_tv_version)
    TextView tvVersion;

    @InjectPresenter
    UserScreenContract.AbstractUserPresenter mPresenter;

    public static UserFragmentView newInstance() {
        return new UserFragmentView();
    }

    @ProvidePresenter
    UserScreenContract.AbstractUserPresenter providePresenter() {
        return new UserPresenter(App.getHasPresenterSubcomponentBuilders(getActivity()));
    }

    @Override
    public void prepareScreen() {
        try {
            Context context = getActivity();
            if (context != null) {
                String version = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
                tvVersion.setText(context.getString(R.string.frg_user_tv_version, version));
            }
        } catch (PackageManager.NameNotFoundException e) {
            tvVersion.setText(getString(R.string.frg_user_app_version_unknown));
        }
    }

    @NonNull
    @Override
    public UserScreenContract.AbstractUserPresenter getPresenter() {
        return mPresenter;
    }

    @NonNull
    @Override
    protected MainScreenContract.AbstractMainRouter getRouter() {
        return ((MainScreenContract.AbstractMainView) getActivity()).getPresenter().getRouter();
    }

    //region OnClick

    @OnClick(R.id.frg_user_btn_send_logs)
    public void onSendLogs() {
        getPresenter().onSendLogs();
    }

    @OnClick(R.id.frg_user_btn_logout)
    public void onLogout() {
        getPresenter().onLogout();
    }

    //endregion

    //region Contract

    @Override
    public void showMemberInfo(Member member) {
        String name = member.getName1() + " " + member.getName2();
        edtName.setText(name);
        edtEmail.setText(member.getEmail());
        edtPassword.setText(member.getPassword());
    }

    //endregion
}
