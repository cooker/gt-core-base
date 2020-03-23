# String 操作

1. 中文汉字和符号判断

    boolean isChinese(char c)

2. 字符串遮盖

        @Test
        public void mark(){
            Assert.assertEquals(GStringUtils.mask("1234567890", 2, "*"), "**34567890");
            Assert.assertEquals(GStringUtils.mask("1234567890", -2, "*"), "12345678**");
            Assert.assertEquals(GStringUtils.mask("1234567890", 3, -2, "*"), "123*****90");
        }

    String mask(String val, int start, int end, String markVal)

3. 转大小写

    String toUpperOrLower(String val, boolean isUpper,int index)

10. Thread 操作

1. 获取当前线程Classloader

    ClassLoader getCurrClassLoader()

2. 获取jar 包内文件地址

    URL getCurrResource(String name)

3. 获取jar包内文件流

    InputStream getCurrResourceInput(String name)