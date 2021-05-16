package exerd.web.ko.main.util;

import exerd.web.ko.main.constants.ExerdConstants;
import org.apache.tomcat.dbcp.dbcp2.*;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPool;
import org.apache.tomcat.dbcp.pool2.impl.GenericObjectPoolConfig;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnector {
    private static final int TIMEOUT = 1000;
    private static final String CONNECTION_URL = "jdbc:apache:commons:dbcp:mysql";
    private static final int MAX_ACTIVE = 7500;
    private static final int MAX_IDLE = 2500;
    private static boolean isInitialized = false;

    public static Connection newConnection() throws SQLException {
        if (!isInitialized) {
            try {
                setUp();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }

        return DriverManager.getConnection(CONNECTION_URL);
    }

    private static void setUp() throws ClassNotFoundException, SQLException {
        if (isInitialized) {
            return;
        }

//        GenericObjectPool pool = new GenericObjectPool(null);
//        pool.setMaxIdle(MAX_IDLE);
//        pool.setMaxTotal(MAX_ACTIVE);
//
//
////        MAX_ACTIVE, GenericKeyedObjectPool.WHEN_EXHAUSTED_BLOCK, TIMEOUT, MAX_IDLE
//
//        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(ExerdConstants.DB_JDBC_URL, ExerdConstants.DB_USER, ExerdConstants.DB_PASSWORD);
//
////        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, pool, null, null, false, true);
//        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
//        poolableConnectionFactory.setPool(pool);


        ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(ExerdConstants.DB_JDBC_URL, ExerdConstants.DB_USER, ExerdConstants.DB_PASSWORD);
        PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(connectionFactory, null);
        poolableConnectionFactory.setValidationQuery("select 1");
        GenericObjectPoolConfig genericObjectPoolConfig = new GenericObjectPoolConfig();
        genericObjectPoolConfig.setTestWhileIdle(true);
        genericObjectPoolConfig.setMaxIdle(MAX_IDLE);
        genericObjectPoolConfig.setMaxTotal(MAX_ACTIVE);

        GenericObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(poolableConnectionFactory, genericObjectPoolConfig);
        poolableConnectionFactory.setPool(connectionPool);

        Class.forName(ExerdConstants.DB_DRIVER_CLASS);

        PoolingDriver driver = new PoolingDriver();
//        driver.registerPool(ExerdConstants.DB_SUB_PROTOCOL_NAME, pool);
        driver.registerPool(ExerdConstants.DB_SUB_PROTOCOL_NAME,connectionPool);
        isInitialized = true;
    }

    public static void main(String[] args) throws SQLException {
        Connection newConnection = DBConnector.newConnection();
        System.out.println(newConnection);
    }
}
