package org.grant.zm.spring2.database.subtable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.grant.zm.spring2.annotation.GSubTableType;
import org.grant.zm.spring2.annotation.GTableType;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * grant
 * 1/4/2020 9:36 上午
 * 描述：
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GSubTableConf {
    private GTableType tableType;
    private GSubTableType subTableType;
    private String[] dataSource;


    public void load(String propVal){
        String[] strs = propVal.split(",");
        this.tableType = GTableType.valueOf(strs[0]);
        this.subTableType = GSubTableType.valueOf(strs[1]);
        this.dataSource = Stream.of(strs).skip(2).collect(Collectors.toList()).toArray(new String[0]);

    }
}
