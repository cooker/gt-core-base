# WebService 工具

GSoapUtils

    数据模拟
    需存在，XmlType、XmlElement 注解
    @Data
    @XmlType(name = "SoapTestEntity", propOrder = {
        "lines"
    })
    public class SoapTestEntity {
        @XmlElement(name = "line")
        List<String> line;
    
    }

1. soap 数据模拟

    String makeTestData(String soapEnv, String sec, String methodName, GSoapParam... param)