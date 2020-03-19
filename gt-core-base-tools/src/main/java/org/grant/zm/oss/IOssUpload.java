package org.grant.zm.oss;

import java.io.File;
import java.io.IOException;

/**
 * grant
 * 19/3/2020 2:44 下午
 * 描述：oss 上传
 */
public interface IOssUpload {
    String upload(File file, String token) throws IOException;

    String upload(File file, String username, String repo, String token) throws IOException;
}
