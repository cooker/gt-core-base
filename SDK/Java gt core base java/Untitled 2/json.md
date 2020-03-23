# json操作

对输出格式美化
    反序列化忽略未知字段
    序列化忽略null字段
    消除数值类型，出现科学计数法问题

GJsonUtils

1. 转json

    String toJson(Object obj)

2. 转bean

    <T> T toBean(String json, Class<T> cl)