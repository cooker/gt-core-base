package org.grant.zm.spring2.async;

import org.grant.zm.spring2.async.ic.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * grant
 * 19/3/2020 9:49 下午
 * 描述：
 */
@Async
@Component
public class GAsyncCall {

    public void newCall(IGAsyncCall$0 call){
        call.call();
    }

    public <A> void newCall(IGAsyncCall$1<A> call, A a){
        call.call(a);
    }

    public <A,B> void newCall(IGAsyncCall$2<A, B> call, A a, B b){
        call.call(a, b);
    }

    public <A, B, C> void newCall(IGAsyncCall$3<A, B, C> call, A a, B b, C c){
        call.call(a,b,c);
    }

    public <A, B, C, D> void newCall(IGAsyncCall$4<A, B, C, D> call, A a, B b, C c, D d){
        call.call(a,b,c,d);
    }

    public <A, B, C, D, E> void newCall(IGAsyncCall$5<A, B, C, D, E> call, A a, B b, C c, D d, E e){
        call.call(a,b,c,d,e);
    }

    public <A, B, C, D, E, F> void newCall(IGAsyncCall$6<A, B, C, D, E, F> call, A a, B b, C c, D d, E e, F f){
        call.call(a,b,c,d,e,f);
    }

    public <A, B, C, D, E, F, G> void newCall(IGAsyncCall$7<A, B, C, D, E, F, G> call, A a, B b, C c, D d, E e, F f, G g){
        call.call(a,b,c,d,e,f,g);
    }

    public <A, B, C, D, E, F, G, H> void newCall(IGAsyncCall$8<A, B, C, D, E, F, G, H> call, A a, B b, C c, D d, E e, F f, G g, H h){
        call.call(a,b,c,d,e,f,g,h);
    }

    public <A, B, C, D, E, F, G, H, I> void newCall(IGAsyncCall$9<A, B, C, D, E, F, G, H, I> call, A a, B b, C c, D d, E e, F f, G g, H h, I i){
        call.call(a,b,c,d,e,f,g,h,i);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void newCallTx(IGAsyncCall$0 call){
        call.call();
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A> void newCallTx(IGAsyncCall$1<A> call, A a){
        call.call(a);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A,B> void newCallTx(IGAsyncCall$2<A, B> call, A a, B b){
        call.call(a, b);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A, B, C> void newCallTx(IGAsyncCall$3<A, B, C> call, A a, B b, C c){
        call.call(a,b,c);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A, B, C, D> void newCallTx(IGAsyncCall$4<A, B, C, D> call, A a, B b, C c, D d){
        call.call(a,b,c,d);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A, B, C, D, E> void newCallTx(IGAsyncCall$5<A, B, C, D, E> call, A a, B b, C c, D d, E e){
        call.call(a,b,c,d,e);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A, B, C, D, E, F> void newCallTx(IGAsyncCall$6<A, B, C, D, E, F> call, A a, B b, C c, D d, E e, F f){
        call.call(a,b,c,d,e,f);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A, B, C, D, E, F, G> void newCallTx(IGAsyncCall$7<A, B, C, D, E, F, G> call, A a, B b, C c, D d, E e, F f, G g){
        call.call(a,b,c,d,e,f,g);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A, B, C, D, E, F, G, H> void newCallTx(IGAsyncCall$8<A, B, C, D, E, F, G, H> call, A a, B b, C c, D d, E e, F f, G g, H h){
        call.call(a,b,c,d,e,f,g,h);
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public <A, B, C, D, E, F, G, H, I> void newCallTx(IGAsyncCall$9<A, B, C, D, E, F, G, H, I> call, A a, B b, C c, D d, E e, F f, G g, H h, I i){
        call.call(a,b,c,d,e,f,g,h,i);
    }

}
