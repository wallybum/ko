package exerd.web.ko.main.constants;

import java.text.MessageFormat;

public class ExerdConstants {
    public static final int CURRENT_MAJOR_VERSION = 3;

    public static final String BASE_URL = "exerd.com";
//    public static final String DB_DRIVER_CLASS = "com.mysql.jdbc.Driver";
//    public static final String DB_SUB_PROTOCOL_NAME = "mysql";
//    public static final String DB_USER = "root";
//    public static final String DB_PASSWORD = "labtomato";
//    public static final String DB_HOST = "exerd.com"; // 로컬에서 테스트 할시 주석 풀 것
//    public static final String DB_PORT = "3306";
//    public static final String DB_NAME = "exerd";
//    public static final String DB_JDBC_URL = MessageFormat.format("jdbc:{0}://{1}:{2}/{3}", DB_SUB_PROTOCOL_NAME, DB_HOST, DB_PORT, DB_NAME);

    /*로컬 테스트용*/
    public static final String DB_DRIVER_CLASS = "com.mysql.cj.jdbc.Driver";
    public static final String DB_SUB_PROTOCOL_NAME = "mysql";
    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "tpffnrtm1!";
    public static final String DB_HOST = "localhost";
    public static final String DB_PORT = "3306";
    public static final String DB_NAME = "exerd";
    public static final String DB_JDBC_URL = MessageFormat.format("jdbc:{0}://{1}:{2}/{3}", DB_SUB_PROTOCOL_NAME, DB_HOST, DB_PORT, DB_NAME);

}
