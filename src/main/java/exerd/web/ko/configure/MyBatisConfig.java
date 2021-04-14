package exerd.web.ko.configure;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
@MapperScan(
        basePackages="exerd.web.ko",
        sqlSessionFactoryRef = "customerSessionFactory",
        sqlSessionTemplateRef = "customerSessionTemplate")


public class MyBatisConfig {

    @Bean(name="customerDataSource")
    @Primary
    public DataSource dataSource() {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://exerd.com:3306/candcomm?characterEncoding=UTF-8&serverTimezone=UTC")
                .driverClassName("com.mysql.cj.jdbc.Driver")
                .username("cand")
                .password("cand01")
                .build();
    }

    @Bean(name="customerSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
        sessionFactoryBean.setDataSource(dataSource);

        PathMatchingResourcePatternResolver pmrp = new PathMatchingResourcePatternResolver();
        sessionFactoryBean.setMapperLocations(pmrp.getResources("classpath:mapper/*.xml"));
        sessionFactoryBean.setTypeHandlersPackage("exerd.web.ko");
        return sessionFactoryBean.getObject();
    }

    @Bean(name="customerSessionTemplate")
    @Primary
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) throws Exception{
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
