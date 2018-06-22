package com.example.catchman.studlife.network;

/**
 * Created by catchman on 6/14/18.
 */

import java.io.IOException;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import com.google.gson.GsonBuilder;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by alan on 20.12.2017.
 */

public class Api {
    private static final int TIMEOUT = 30000;

    private static Api instance;


    public static Api getInstance() {
        if (instance == null) {
            instance = new Api();
        }
        return instance;
    }

    private Retrofit retrofit;
    private WebService webService;

    public Api() {
        retrofit = buildRetrofit();
    }

    private Retrofit buildRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(WebParams.BASE_URL)
                .client(buildUnsafeOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(new GsonBuilder().disableHtmlEscaping().create()))
                .build();
    }

    public WebService getWebService() {
        if (webService == null) {
            webService = retrofit.create(WebService.class);
        }
        return webService;
    }

    private OkHttpClient buildUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) throws CertificateException {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory);
            builder.hostnameVerifier(new HostnameVerifier() {
                @Override
                public boolean verify(String hostname, SSLSession session) {
                    return true;
                }
            });


            builder.readTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
                    .retryOnConnectionFailure(true)
                    .addInterceptor(new MyInterceptor());
            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class MyInterceptor implements Interceptor {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();

            Request.Builder builder = request.newBuilder();
            boolean multipart = false;

            try {
                if (request.body().contentType().toString().contains("multipart")) {
                    multipart = true;
                }
            } catch (Exception ignored) {

            }

//            if (StudLifeApp.getInstance().getAuth() != null) {
//                builder.addHeader(WebParams.HEADER_AUTHORIZATION, SpearId.getInstance().getAuth());
//            }
          /*  if (multipart) {
                builder.addHeader(WebParams.HEADER_CONTENT_TYPE, "multipart/form-data");
            }*/

            /*  builder.addHeader("boundary", "alamofire.boundary.0eae61058b9f64ce");
            builder.addHeader("Accept-Language", "ru-UA;q=1.0, en-US;q=0.9");
            if (request.body() != null) {
                builder.addHeader("Content-Length", request.body().contentLength() + "");
            }
            builder.addHeader("User-Agent", "eSpear/1.0 (com.digitaledge.espearid; build:4; iOS 11.2.6) Alamofire/4.6.0");
            builder.addHeader("Accept-Encoding", "gzip;q=1.0, compress;q=0.5");*/
            request = builder.build();
            long t1 = System.nanoTime();
            System.out.println(
                    String.format("Sending request %s on %n%s , %s", request.url(), request.headers(), request.body()));

            Response response = chain.proceed(request);
            long t2 = System.nanoTime();

            System.out.println(
                    String.format("Received response for %s in %.1fms%n%s", response.request().

                                    url(),
                            (t2 - t1) / 1e6d, response.headers()));
            return response;
        }
    }
}

