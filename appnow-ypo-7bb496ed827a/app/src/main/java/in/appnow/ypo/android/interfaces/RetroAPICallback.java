package in.appnow.ypo.android.interfaces;

import android.support.annotation.Nullable;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by sonu on 24/10/17.
 */

public interface RetroAPICallback {

    public void onResponse(Call<?> call, Response<?> response, int requestCode, @Nullable Object request);

    public void onFailure(Call<?> call, Throwable t, int requestCode, @Nullable Object request);

    public void onNoNetwork(int requestCode);
}
