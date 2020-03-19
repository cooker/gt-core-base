package org.grant.zm.spring2.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * grant
 * 19/3/2020 9:49 下午
 * 描述：
 */
@Async
@Component
public class GAsyncCall {

    public void newCall(Call call){
        call.call();
    }

    @FunctionalInterface
    public static interface Call{
        void call();
    }
}
