package com.byandev.trackusers.Api;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class RetrofitClient {


    private static Retrofit retrofit = null;

  public static Retrofit getClient(String baseUrl) {

    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
//        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    client.addInterceptor(new Interceptor() {
      @Override
      public Response intercept(Chain chain) throws IOException {
        String token = SharedPrefManager.SP_TOKEN;
        Log.d("token",token);
        Request request = chain.request()
            .newBuilder()
            .addHeader("Authorization", token)
            .build();
        return chain.proceed(request);
      }
    });

    if (retrofit == null) {
      retrofit = new Retrofit.Builder()
          .baseUrl(baseUrl)
          .addConverterFactory(GsonConverterFactory.create())
          .client(client.build())
          .build();
    }
    return retrofit;
  }

//    private static final String CACHE_CONTROL = "Cache-Control";
//
//    public static Retrofit getClient(String baseUrl) {
//        if (retrofit == null) {
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(UtilsApi.BASE_URL_API)
//                    .client( provideOkHttpClient() )
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//    private static OkHttpClient provideOkHttpClient ()
//    {
//
//        final SharedPrefManager sharedPrefManager = new SharedPrefManager(AppController.getInstance());
//
//        return new OkHttpClient.Builder()
//                .readTimeout(1, TimeUnit.MINUTES)
//                .connectTimeout(1, TimeUnit.MINUTES)
//                .writeTimeout(2,TimeUnit.MINUTES)
//                .addInterceptor(provideHttpLoggingInterceptor())
//                .addInterceptor(provideOfflineCacheInterceptor())
//                .addNetworkInterceptor(provideCacheInterceptor())
//                .addNetworkInterceptor(new Interceptor() {
//                    @Override
//                    public Response intercept(Chain chain) throws IOException {
//                        String token = sharedPrefManager.getSpToken();
//                        Request request = chain.request()
//                                .newBuilder()
//                                .addHeader("Authorization", token)
//                                .build();
//                        return chain.proceed(request);
//                    }
//                })
//                .cache(provideCache())
//                .build();
//    }
//
//    private static Cache provideCache ()
//    {
//        Cache cache = null;
//        try
//        {
//            cache = new Cache( new File( AppController.getInstance().getCacheDir(), "http-cache" ),
//                    10 * 1024 * 1024 ); // 10 MB
//        }
//        catch (Exception e)
//        {
//            Timber.e(  "Could not create Cache!" );
//        }
//        return cache;
//    }
//
//    private static HttpLoggingInterceptor provideHttpLoggingInterceptor ()
//    {
//        HttpLoggingInterceptor httpLoggingInterceptor =
//                new HttpLoggingInterceptor( new HttpLoggingInterceptor.Logger()
//                {
//                    @Override
//                    public void log (String message)
//                    {
//                        Timber.e( message );
//                    }
//                } );
//        httpLoggingInterceptor.setLevel( BuildConfig.DEBUG ? HEADERS : NONE  );
//        return httpLoggingInterceptor;
//    }
//
//    public static Interceptor provideCacheInterceptor ()
//    {
//        return new Interceptor()
//        {
//            @Override
//            public Response intercept (Chain chain) throws IOException
//            {
//                Response response = chain.proceed( chain.request() );
//
//                // re-write response header to force use of cache
//                CacheControl cacheControl = new CacheControl.Builder()
//                        .maxAge( 1, TimeUnit.SECONDS )
//                        .build();
//
//                return response.newBuilder()
//                        .header( CACHE_CONTROL, cacheControl.toString() )
//                        .build();
//            }
//        };
//    }
//
//    public static Interceptor provideOfflineCacheInterceptor ()
//    {
//        return new Interceptor()
//        {
//            @Override
//            public Response intercept (Chain chain) throws IOException
//            {
//                Request request = chain.request();
//
//                if ( !AppController.hasNetwork() )
//                {
//                    CacheControl cacheControl = new CacheControl.Builder()
//                            .maxStale( 1, TimeUnit.DAYS )
//                            .build();
//
//                    request = request.newBuilder()
//                            .cacheControl( cacheControl )
//                            .build();
//                }
//
//                return chain.proceed( request );
//            }
//        };
//    }

}