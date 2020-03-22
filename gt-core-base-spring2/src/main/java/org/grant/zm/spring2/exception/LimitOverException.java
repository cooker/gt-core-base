package org.grant.zm.spring2.exception;

import lombok.Getter;

/**
 * ZoomGrant 2020/3/22 10:23
 */
@Getter
public class LimitOverException extends RuntimeException {

    public LimitOverException(String msg){
        super(msg);
    }
}
