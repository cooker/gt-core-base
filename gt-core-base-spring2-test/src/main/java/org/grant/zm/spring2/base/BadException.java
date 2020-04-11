package org.grant.zm.spring2.base;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * grant
 * 11/4/2020 2:37 下午
 * 描述：
 */
@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "sasa")
public class BadException extends RuntimeException {

    public BadException(String message) {
        super(message);
    }
}
