package org.grant.zm;

import org.junit.Test;

/**
 * ZoomGrant 2020/3/1
 */
public class GExceptionUtilsTest {

    @Test
    public void sa(){
        try {
            int i = 1/0;
        }catch (Throwable e){
            StackTraceElement[] st = e.getStackTrace();
            for (int i = 0; i < st.length; i++) {
                StackTraceElement s = st[i];
                System.out.println(s.getClassName() + "::" + s.getMethodName());
            }
        }

    }
    
}
