package org.grant.zm.spring2.database;

import lombok.Data;
import org.grant.zm.spring2.annotation.GOperationType;

import java.io.Serializable;

/**
 * grant
 * 19/3/2020 9:10 下午
 * 描述：
 */
@Data
public class GLogEntity implements Serializable {
    private static final long serialVersionUID = 4466588220877123353L;
    /**
     * 业务名称
     */
    String value;

    /**
     * 操作类型
     */
    GOperationType operationType;

    /**
     * 耗时
     */
    long time;

    /**
     * 业务号
     */
    String businessNo;

    String params;

    String header;

    String body;

    Class<? extends IGLogInsertHandler> insertHandler;

    @Override
    public GLogEntity clone() {
        GLogEntity gLogEntity = new GLogEntity();
        gLogEntity.setValue(this.value);
        gLogEntity.setOperationType(this.operationType);
        gLogEntity.setTime(this.time);
        gLogEntity.setBusinessNo(this.businessNo);
        gLogEntity.setParams(this.params);
        gLogEntity.setHeader(this.header);
        gLogEntity.setBody(this.body);
        gLogEntity.setInsertHandler(this.insertHandler);
        return gLogEntity;
    }
}
