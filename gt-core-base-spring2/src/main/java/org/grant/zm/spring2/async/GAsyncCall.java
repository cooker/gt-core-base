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
    public <T> void newCall(Call$1<T> call, T arg1){
        call.call(arg1);
    }
    public <T,A> void newCall(Call$2<T, A> call, T arg1, A arg2){
        call.call(arg1, arg2);
    }

    @FunctionalInterface
    public static interface Call{
        void call();
    }

    @FunctionalInterface
    public static interface Call$1<T>{
        void call(T arg1);
    }

    @FunctionalInterface
    public static interface Call$2<T, A>{
        void call(T arg1, A arg2);
    }
}
