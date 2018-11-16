package in.appnow.ypo.android.dagger.module;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.io.File;
import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import in.appnow.ypo.android.BuildConfig;
import in.appnow.ypo.android.dagger.scope.AppScope;
import in.appnow.ypo.android.helper.PreferenceManger;
import in.appnow.ypo.android.rest.APIInterface;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class NetworkModule {

    @AppScope
    @Provides
    public Cache cache(File cacheFile) {
        return new Cache(cacheFile, 10 * 1024 * 1024);//10MB Cache
    }

    @AppScope
    @Provides
    public File cacheFile(Context context) {
        return new File(context.getCacheDir(), "okhttp_cache");
    }

    @AppScope
    @Provides
    public OkHttpClient okHttpClient(HttpLoggingInterceptor loggingInterceptor, Cache cache, Context context, PreferenceManger preferenceManger) {
        OkHttpClient.Builder client = new OkHttpClient.Builder();

      /*  if (SslUtils.INSTANCE.getTrustAllHostsSSLSocketFactory() != null) {
            client.sslSocketFactory(SslUtils.INSTANCE.getTrustAllHostsSSLSocketFactory());
        }

        client.sslSocketFactory(SslUtils.INSTANCE.getSslContextForCertificateFile(context, RestUtils.getCertFileName()).getSocketFactory());
*/
        client.readTimeout(5, TimeUnit.MINUTES)
                .connectTimeout(5, TimeUnit.MINUTES)
                .writeTimeout(5, TimeUnit.MINUTES)
                .addInterceptor(loggingInterceptor)
                .cache(cache);
        return client.build();
    }

    @AppScope
    @Provides
    public HttpLoggingInterceptor httpLoggingInterceptor() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);
        return interceptor;
    }

    @AppScope
    @Provides
    public Retrofit retrofit(OkHttpClient okHttpClient, Gson gson) {
        return new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(BuildConfig.END_POINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    @AppScope
    @Provides
    public APIInterface apiInterface(Retrofit retrofit) {
        return retrofit.create(APIInterface.class);
    }

    @AppScope
    @Provides
    public Gson gson() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setLenient();
        //gsonBuilder.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        //gsonBuilder.registerTypeAdapter(DateTime.class, new DateTimeConverter());
        gsonBuilder.create();
     //   return Converters.registerAll(gsonBuilder)
           return gsonBuilder.create();
    }


}
