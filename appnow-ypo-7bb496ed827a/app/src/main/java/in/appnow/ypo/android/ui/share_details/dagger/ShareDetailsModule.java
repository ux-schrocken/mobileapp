package in.appnow.ypo.android.ui.share_details.dagger;

import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsModel;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsPresenter;
import in.appnow.ypo.android.ui.share_details.mvp.ShareDetailsView;

/**
 * Created by sonu on 18:40, 23/10/18
 * Copyright (c) 2018 . All rights reserved.
 */
@Module
public class ShareDetailsModule {
    private final AppCompatActivity appCompatActivity;

    public ShareDetailsModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    @ShareDetailsScope
    public ShareDetailsView shareDetailsView() {
        return new ShareDetailsView(appCompatActivity);
    }

    @Provides
    @ShareDetailsScope
    public ShareDetailsModel shareDetailsModel(APIInterface apiInterface) {
        return new ShareDetailsModel(appCompatActivity, apiInterface);
    }

    @Provides
    @ShareDetailsScope
    public ShareDetailsPresenter shareDetailsPresenter(ShareDetailsView view, ShareDetailsModel model) {
        return new ShareDetailsPresenter(view, model);
    }
}
