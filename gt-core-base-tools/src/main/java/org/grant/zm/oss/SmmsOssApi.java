package org.grant.zm.oss;

import okhttp3.*;
import org.grant.zm.utils.GJsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * grant
 * 19/3/2020 2:47 下午
 * 描述：
 */
public class SmmsOssApi implements IOssUpload{
    public static final String url = "https://sm.ms/api/v2/upload";

    @Override
    public String upload(File file, String token) throws IOException {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
        MultipartBody body  = new MultipartBody.Builder()
                .setType(MediaType.parse("multipart/form-data"))
                .addFormDataPart("file", file.getName(), requestBody)
                .build();

        Request request = new Request.Builder().url(url)
                .addHeader("Authorization", token)
                .post(body).build();

        String resp = client.newCall(request).execute().body().string();
        Map<String, Object> mResp = GJsonUtils.toBean(resp, Map.class);
        if ("success".equals(mResp.get("code"))) {
            return ((Map)mResp.get("data")).get("url").toString();
        }else {
            throw new IOException(mResp.get("message").toString());
        }
    }

    @Override
    public String upload(File file, String username, String repo, String token) throws IOException {
        throw  new IOException("不支持");
    }
}
