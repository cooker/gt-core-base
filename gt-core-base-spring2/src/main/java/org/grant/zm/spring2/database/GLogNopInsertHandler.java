package org.grant.zm.spring2.database;

import org.springframework.stereotype.Component;

/**
 * grant
 * 20/3/2020 11:24 上午
 * 描述：
 */
public class GLogNopInsertHandler implements IGLogInsertHandler{

    @Override
    public void insert(GLogEntity gLogEntity) {
        //NOP
    }
}
