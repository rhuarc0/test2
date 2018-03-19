package com.zennex.trl3lg.presentation.module.main.submodule.user.presenter;

import android.support.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.zennex.trl3lg.domain.entities.Member;
import com.zennex.trl3lg.domain.usecases.common.DefaultObserver;
import com.zennex.trl3lg.domain.usecases.member.FetchMember;
import com.zennex.trl3lg.presentation.common.di.presenterbindings.HasPresenterSubcomponentBuilders;
import com.zennex.trl3lg.presentation.model.TitleModel;
import com.zennex.trl3lg.presentation.module.main.submodule.user.UserScreenContract;
import com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.assembly.IUserPresenterSubcomponent;
import com.zennex.trl3lg.presentation.module.main.submodule.user.presenter.assembly.UserPresenterModule;

import javax.inject.Inject;

/**
 * Created by nikita on 22.07.17.
 */
@InjectViewState
public class UserPresenter extends UserScreenContract.AbstractUserPresenter {

    @Inject
    FetchMember fetchMember;

    public UserPresenter(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        super(presenterSubcomponentBuilders);
    }

    @Override
    public void inject(@NonNull HasPresenterSubcomponentBuilders presenterSubcomponentBuilders) {
        ((IUserPresenterSubcomponent.Builder) presenterSubcomponentBuilders.getPresenterComponentBuilder(UserPresenter.class))
                .presenterModule(new UserPresenterModule(this))
                .build()
                .injectMembers(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        fetchMemberInfo();
    }

    @Override
    public void fetchMemberInfo() {
        fetchMember.execute(new FetchMemberObserver(), null);
    }

    @Override
    public void onSendLogs() {

    }

    @Override
    public void onLogout() {
        getRouter().showLoginScreen();
    }

    @Override
    protected TitleModel getTitle() {
        return null;
    }

    private class FetchMemberObserver extends DefaultObserver<Member> {

        private FetchMemberObserver() {
        }

        @Override
        public void onNext(Member member) {
            getViewState().showMemberInfo(member);
        }

        @Override
        public void onError(Throwable e) {
            getViewState().hidePending();
        }

        @Override
        protected void onStart() {
        }

        @Override
        protected String getTag() {
            return FetchMemberObserver.class.getSimpleName();
        }
    }

}
