package in.appnow.ypo.android.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.ui.main.dagger.DaggerMainActivityComponent;
import in.appnow.ypo.android.ui.main.dagger.MainActivityComponent;
import in.appnow.ypo.android.ui.main.dagger.MainActivityModule;
import in.appnow.ypo.android.ui.main.mvp.MainActivityPresenter;
import in.appnow.ypo.android.ui.main.mvp.MainActivityView;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainActivityView view;
    @Inject
    MainActivityPresenter presenter;
    private MainActivityComponent component;

    public static void openMainActivity(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        component = DaggerMainActivityComponent.builder()
                .appComponent(YPOApplication.get(this).component())
                .mainActivityModule(new MainActivityModule(this))
                .build();
        component.inject(this);
        setContentView(view);
        presenter.onCreate();
    }

    public MainActivityComponent getComponent() {
        return component;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        List<Fragment> fragmentList = getSupportFragmentManager().getFragments();
        if (fragmentList.size() > 0) {
            for (Fragment fragment : fragmentList) {
                fragment.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
}
