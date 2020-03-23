# 文件操作

GFileUtils

1. 获取不带扩展名的文件名

    String getFileNameNoEx(String filename)

2. 获取扩展名不带 .

    String getExtensionName(String filename)

3. 文件大小转换

    String getSize(long size)

4. 流转文件

    File inputStreamToFile(InputStream ins, String fileName) throws IOException

5. 文件转Base64

    String toBase64(File file)