package in.appnow.ypo.android.ui.main;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import in.appnow.ypo.android.BuildConfig;
import in.appnow.ypo.android.R;
import in.appnow.ypo.android.app_base.YPOApplication;
import in.appnow.ypo.android.rest.APIInterface;
import in.appnow.ypo.android.rest.response.MemberRequestResponse;
import in.appnow.ypo.android.ui.main.dagger.DaggerMainActivityComponent;
import in.appnow.ypo.android.ui.main.dagger.MainActivityComponent;
import in.appnow.ypo.android.ui.main.dagger.MainActivityModule;
import in.appnow.ypo.android.ui.main.mvp.MainActivityPresenter;
import in.appnow.ypo.android.ui.main.mvp.MainActivityView;
import in.appnow.ypo.android.ui.dashboard_contact.DashboardContactFragment;
import in.appnow.ypo.android.utils.StringUtils;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @Inject
    MainActivityView view;
    @Inject
    MainActivityPresenter presenter;
public int dashboardPositionGlobalVar = -1;
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



        SwipeRefreshLayout pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                presenter.swipe();



                pullToRefresh.setRefreshing(false);
            }
        });
        presenter.swipe();

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
