# 基础扩展

# 常量定义

- org.grant.zm.base.GConstants

    String SUCCESS="success";
    String FAIL="fail";
    
    String CHARSET_UTF8 = "utf-8";
    
    String ENV_PROD = "prod";
    String ENV_DEV = "dev";
    String ENV_SIT = "sit";
    String ENV_UAT = "uat";
    
    long KB = 1024L;
    long MB = 1048576L;
    long GB = 1073741824L;
    long TB = 1099511627776L;
    /**
     * 格式化小数
     */
    DecimalFormat DF = new DecimalFormat("0.00");

# 数据分页

- org.grant.zm.base.GPage
1. GPage<T> page(List<T> datas, Integer currentPage, Integer pageLimit)

    java steam 分页

2. Integer getPage(Integer total, Integer pageLimit)

    获取总页数

# return 扩展

- org.grant.zm.tuple.*
1. GTuple<K, V>