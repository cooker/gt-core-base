package org.grant.zm.spring2.javassist;

import org.springframework.web.bind.annotation.RequestMapping;

/**
 * grant
 * 15/4/2020 9:29 PM
 * 描述：
 */
@UpAnn
@DpAnn
public class UpAnnClass {

    @UpAnn
    @RequestMapping
    public void ss(){
        System.out.println("sasas");
    }
}
