package org.grant.zm.utils;

import com.esotericsoftware.reflectasm.FieldAccess;
import io.vavr.Tuple;
import io.vavr.Tuple2;
import org.grant.zm.soapui.GSoapParam;

import javax.xml.bind.annotation.XmlElement;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;
import java.util.StringJoiner;
import java.util.stream.Stream;

import static org.grant.zm.soapui.GSoapRequestTemplate.*;

/**
 * grant
 * 3/3/2020 11:44 上午
 * 描述：webservice 数据模拟
 */
public class GSoapUtils {

    public static String makeTestData(String soapEnv, String sec, String methodName, GSoapParam... param){
        StringBuilder sb = new StringBuilder("<soapenv:Envelope xmlns:soapenv=\"")
                .append(soapEnv).append("\" xmlns:sec=\"").append(sec).append("\">\n")
                .append("   <soapenv:Header/>\n")
                .append("   <soapenv:Body>\n")
                .append("      <sec:"+methodName+">");

        Stream.of(param).forEach((a)->{
            sb.append(makeTestParam(a, false));
        });

        sb.append("      </sec:"+methodName+">\n" +
                "   </soapenv:Body>\n" +
                "</soapenv:Envelope>");
        return sb.toString();
    }

    private static String makeTestParam(GSoapParam param, boolean b){
        StringJoiner sj = new StringJoiner("",
               b ? "" : String.format(X_KEY_START, param.getNameSpace()),
               b ? "" : String.format(X_KEY_END, param.getNameSpace()));
        Object val = param.getParam();
        if (val == null){
            sj.add(X_VALUE_EMPTY);
        }else {
            Tuple2<Boolean, String> tuple2 = baseTypeTranf(val);
            if (tuple2._1()) {
                sj.add(tuple2._2());
            }else if (List.class.isAssignableFrom(val.getClass())){
                List datas = (List)val;
                for (Object obj : datas){
                    sj.add(makeTestParam(new GSoapParam(param.getNameSpace(), obj), false));
                }
            }else {
                FieldAccess fieldAccess = FieldAccess.get(val.getClass());
                Field[] fields = fieldAccess.getFields();
                for (int i = 0; i < fieldAccess.getFieldCount(); i++) {
                    Field field = fields[i];
                    XmlElement xmlElement = field.getAnnotation(XmlElement.class);
                    String nameSpace = field.getName();
                    if (xmlElement != null) {
                        nameSpace = xmlElement.name();
                    }
                    Object vv = fieldAccess.get(val, i);
                    sj.add(makeTestParam(new GSoapParam(nameSpace, vv), true));
                }
            }
        }

        return sj.toString();
    }

    protected static Tuple2<Boolean, String> baseTypeTranf(Object val){
        String strVal = "";
        Boolean isBase = Boolean.TRUE;
        switch (val.getClass().getName()) {
            case "java.lang.String":
            case "java.lang.Integer":
            case "int":
            case "java.lang.Long":
            case "long":
            case "java.lang.Float":
            case "float":
            case "java.lang.Boolean":
            case "boolean":
            case "java.lang.Double":
            case "double":
            case "java.lang.Short":
            case "short":
                strVal = String.format(X_VALUE, Objects.toString(val));
                break;
            case "java.math.BigDecimal":
                BigDecimal bigDecimal = (BigDecimal) val;
                strVal = String.format(X_VALUE, bigDecimal.toPlainString());
                break;
            default:
                isBase = Boolean.FALSE;
                break;
        }
        return Tuple.of(isBase, strVal);
    }

}
