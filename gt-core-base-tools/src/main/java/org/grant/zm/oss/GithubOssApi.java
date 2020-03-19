package org.grant.zm.oss;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import org.grant.zm.utils.GFileUtils;
import org.grant.zm.utils.GJsonUtils;

import java.io.File;
import java.io.IOException;
import java.util.Map;

/**
 * grant
 * 19/3/2020 2:47 下午
 * 描述：
 */
public class GithubOssApi implements IOssUpload{
    public static final String url = "https://api.github.com/repos/:owner/:repo/contents/:file";

    @Override
    public String upload(File file, String token) throws IOException {
        throw new IOException("不支持");
    }

    @Override
    public String upload(File file, String username, String repo, String token) throws IOException {
        String nUrl = url.replaceAll(":owner", username).replaceAll(":repo", repo).replaceAll(":file", file.getName());
        OkHttpClient okHttpClient = new OkHttpClient();

        String content = GFileUtils.toBase64(file);
        StringBuilder sb = new StringBuilder("{\"message\": \"oss put\",");
        sb.append("\"content\": \"").append(content).append("\"}");
        RequestBody requestBody = RequestBody.create(sb.toString(), MediaType.parse("application/json"));

        Request request = new Request.Builder()
                .url(nUrl)
                .addHeader("Authorization", "token " + token)
                .addHeader("Content-Type", "application/json")
                .put(requestBody)
                .build();
        String resp = okHttpClient.newCall(request).execute().body().string();
        Map<String, Object> mResp = GJsonUtils.toBean(resp, Map.class);
        return ((Map)mResp.get("content")).get("download_url").toString();
    }
}
