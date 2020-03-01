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

}
