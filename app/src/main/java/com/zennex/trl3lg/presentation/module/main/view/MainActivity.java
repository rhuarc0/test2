package com.zennex.trl3lg.presentation.module.main.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.zennex.trl3lg.R;
import com.zennex.trl3lg.data.entity.RentalGroup;
import com.zennex.trl3lg.presentation.common.annotations.Layout;
import com.zennex.trl3lg.presentation.module.app.App;
import com.zennex.trl3lg.presentation.module.main.MainScreenContract;
import com.zennex.trl3lg.presentation.module.main.presenter.MainPresenter;
import com.zennex.trl3lg.presentation.module.main.submodule.catalog.CatalogScreenContract;
import com.roughike.bottombar.BottomBar;

import org.cryse.widget.persistentsearch.PersistentSearchView;
import org.cryse.widget.persistentsearch.SimpleSearchListener;

import butterknife.BindView;

/**
 * Created by nikita on 22.07.17.
 */

@Layout(R.layout.act_main)
public class MainActivity extends MainScreenContract.AbstractMainView
        implements CatalogScreenContract.ICatalogEventListener {


    @InjectPresenter
    MainScreenContract.AbstractMainPresenter mPresenter;

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.act_main_bottom_bar)
    BottomBar mBottomBar;
    @BindView(R.id.searchview)
    PersistentSearchView mSearchView;


    public static Intent newIntent(@NonNull Context context) {
        return new Intent(context, MainActivity.class);
    }

    @ProvidePresenter
    public MainScreenContract.AbstractMainPresenter providePresenter() {
        return new MainPresenter(App.getHasPresenterSubcomponentBuilders(this));
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setSupportActionBar(mToolbar);
        mBottomBar.setOnTabSelectListener(tabId -> {
            if (mBottomBar.getCurrentTabId() != tabId) {
                if (tabId == R.id.tab_catalog) getPresenter().onCatalogTabSelected();
                else if (tabId == R.id.tab_on_demand) getPresenter().onOnDemandTabSelected();
                else if (tabId == R.id.tab_rental) getPresenter().onRentalTabSelected();
                else if (tabId == R.id.tab_user) getPresenter().onUserTabSelected();
            }
        });

        mSearchView.setSearchListener(new SimpleSearchListener() {
            @Override
            public void onSearch(String query) {
                getPresenter().onClickedBtnSearch(query);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBottomBar.removeOnTabSelectListener();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                openSearch();
                return true;

        }
        return super.onOptionsItemSelected(item);
    }

    public void openSearch() {
        View menuItemView = findViewById(R.id.action_search);
        mSearchView.setStartPositionFromMenuItem(menuItemView);
        mSearchView.openSearch();
    }

    @NonNull
    @Override
    public MainScreenContract.AbstractMainPresenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void prepareScreen() {

    }

    @Override
    public void setTitleText(@StringRes int titleText) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(titleText);
    }

    @Override
    public void setTitleText(String title) {
        if (getSupportActionBar() != null) getSupportActionBar().setTitle(title);
    }


    @Override
    public void setVisibilityBackButton(boolean value) {
        if (getSupportActionBar() != null) getSupportActionBar().setDisplayHomeAsUpEnabled(value);
    }


    //region CatalogScreenContract.ICatalogEventListener

    @Override
    public void onRentalGroupSelected(RentalGroup group) {
        mPresenter.onRentalGroupSelected(group, mSearchView.getSearchText());
    }

    //endregion CatalogScreenContract.ICatalogEventListener

}
