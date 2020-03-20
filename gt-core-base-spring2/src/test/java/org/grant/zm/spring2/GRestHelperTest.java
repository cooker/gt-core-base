package org.grant.zm.spring2;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * grant
 * 20/3/2020 6:07 下午
 * 描述：
 */
public class GRestHelperTest {
    @Test
    public void getBaidu() throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .connectTimeout(10, TimeUnit.SECONDS)//设置超时时间
                .readTimeout(10, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(10, TimeUnit.SECONDS)//设置写入超时时间
                .build();

        Request.Builder okBuilder = new Request.Builder();
        okBuilder.url("http://baidu.com:80/test/?hello=a");
        Request request = okBuilder.get().header("accept-encoding", "utf-8").build();
        System.out.println(client.newCall(request).execute().body().string());
    }
}
