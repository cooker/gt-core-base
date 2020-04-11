package org.grant.zm.spring2.async.ic;

/**
 * grant
 * 30/3/2020 4:22 下午
 * 描述：
 */
@FunctionalInterface
public interface IGAsyncCall_3<A, B, C> {
    void call(A a, B b, C c);
}
