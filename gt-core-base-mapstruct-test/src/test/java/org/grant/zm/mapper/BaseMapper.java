package org.grant.zm.mapper;


import java.util.List;

public interface BaseMapper<A, B> {

    A toA(B b);

    B toB(A a);


    List<A> toAs(List<B> bs);

    List<B> toBs(List<A> as);

}
