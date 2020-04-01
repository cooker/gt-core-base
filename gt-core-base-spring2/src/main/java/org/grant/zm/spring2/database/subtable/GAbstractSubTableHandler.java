package org.grant.zm.spring2.database.subtable;

import lombok.Getter;
import org.apache.commons.lang3.StringUtils;
import org.grant.zm.spring2.annotation.GSubTableType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;

/**
 * grant
 * 31/3/2020 3:47 下午
 * 描述：
 */
@Getter
public abstract class GAbstractSubTableHandler implements IGSubTableHandler{
    String format;
    GSubTableType subTableType;
    LocalDateTime date;

    public void loadDate(LocalDateTime date){
        this.date = date;
    }

    public GAbstractSubTableHandler(String format, GSubTableType subTableType){
        this.format = format;
        this.subTableType = subTableType;
    }

    @Override
    public boolean exists(String tableName, DataSource dataSource, String subName) {
        boolean isOk = false;
        try(Connection con = dataSource.getConnection()) {
            con.setAutoCommit(true);
            PreparedStatement ps = con.prepareStatement(IGSubTableSQL.MYSQL_EXIST_SQL);
            ps.setString(1, tableName + "_" + subName);
            ResultSet rs = ps.executeQuery();
            if (rs.next() == false) return false;
            String tb = rs.getString("tb");
            if (StringUtils.isNotEmpty(tb)) isOk = true;
            ps.close();
        }catch (Exception e){
            e.printStackTrace();
            getLogger().error("分表判断表是否存在错误: {}", (tableName + "_" + subName), e);
        }
        return isOk;
    }

    abstract protected String realSubName(int am);
}
