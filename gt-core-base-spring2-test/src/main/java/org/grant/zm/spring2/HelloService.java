package org.grant.zm.spring2;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * ZoomGrant 2020/3/21 12:42
 */
@Slf4j
@Component
public class HelloService {

    public void say(){
      log.info("say ======================================");
    }
}
