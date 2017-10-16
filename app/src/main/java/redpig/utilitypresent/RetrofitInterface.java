package redpig.utilitypresent;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by icwer on 2017-10-16.
 */

public interface RetrofitInterface {
    public static final String API_URL = "http://roemilk.iptime.org:2457";

    @GET("/sess-bin/captcha.cgi?act=nameonly")
    Call<ResponseBody>getPageBody();
}
