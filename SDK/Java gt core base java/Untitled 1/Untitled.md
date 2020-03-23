# 配置操作

GConfigUtils

[文件系统配置文件](Untitled/Untitled.md)

[Jar 包内配置文件](Untitled/Jar.md)

配置读取

    支持文件格式 *.properties
    支持：操作系统没文件（GFileConfig）、jar 包内文件（GClassConfig）

<T extends GAbstractConfig> GIConfig getConfig(String filePath, Class<T> cl)