package org.grant.zm.utils;

/**
 * ZoomGrant 2020/2/28
 */
public class GStringUtils {

    public static String toUpper(String val, int index){
        return toUpperOrLower(val, true, index);
    }

    public static String toLower(String val, int index){
        return toUpperOrLower(val, false, index);
    }

    /**
     * 转大/小写
     * @param val
     * @param index
     * @return
     */
    public static String toUpperOrLower(String val, boolean isUpper,int index){
        if (index == -1){
            if (isUpper){
                return val.toUpperCase();
            }else {
                return val.toLowerCase();
            }
        }

        char ch = val.charAt(index);
        StringBuilder sb = newBuilder();
        int len = val.length();
        sb.append(val.substring(0, index));

        sb.append(
            isUpper ? Character.toUpperCase(ch) : Character.toLowerCase(ch)
        );
        index = index + 1;
        if (index < len) {
            sb.append(val.substring(index));
        }
        return sb.toString();
    }

    public static String toCamel(String val){
        StringBuilder sb = newBuilder();
        String strs[] = val.split("_");
        int len = strs.length;
        sb.append(strs[0]);
        for (int j = 1; j < strs.length; j++) {
            sb.append(toUpperOrLower(strs[j], true, 0));
        }
        return sb.toString();
    }

    public static String tableName(String val){
        StringBuilder sb = newBuilder();
        char[] arr = val.toCharArray();
        sb.append(Character.toLowerCase(arr[0]));
        for (int i = 1; i < arr.length; i++) {
            if (Character.isUpperCase(arr[i])){
                sb.append("_");
            }
            sb.append(Character.toLowerCase(arr[i]));
        }
        return sb.toString();

    }


    public static StringBuilder newBuilder(){
        return new StringBuilder();
    }

    public static StringBuilder newBuilder(String str){
        return new StringBuilder(str);
    }

    /**
     * 根据Unicode编码完美的判断中文汉字和符号
     * @param c
     * @return
     */
    public static boolean isChinese(char c) {
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
            || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_B
            || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
            || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS
            || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION) {
            return true;
        }
        return false;
    }

    /**
     * 遮盖
     * @param val 原字符串
     * @param start 起始位置
     * @param end 结束位置
     * @param markVal 替换内容
     * @return
     */
    public static String mask(String val, int start, int end, String markVal){
        if (start > 0 && end < 0){
            if (start > val.length() + end) {
                throw new IndexOutOfBoundsException("start > length() + end");
            }
        }
        StringBuilder sb = newBuilder();
        if (start > 0) {
            sb.append(val.substring(0, start));
        }
        if (end > 0){
            return sb.append(val.substring(start, end).replaceAll(".", markVal))
                    .append(val.substring(end, val.length())).toString();
        }else {
            return sb.append(
                    start != 0 ? val.substring(start, val.length() + end).replaceAll(".", markVal) :
                            val.substring(start, val.length() + end)
            ).append(
                    start !=0 ? val.substring(val.length() + end, val.length()) :
                            val.substring(val.length() + end, val.length()).replaceAll(".", markVal)
            ).toString();
        }
    }

    /**
     * @see GStringUtils#mask(String, int, int, String)
     * @param val
     * @param num
     * @param markVal
     * @return
     */
    public static String mask(String val, int num, String markVal){
        return mask(val,0, num, markVal);
    }
}
