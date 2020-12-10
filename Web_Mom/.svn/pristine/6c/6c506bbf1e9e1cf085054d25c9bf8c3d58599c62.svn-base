package com.example.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Class Name : MybatisSimpleConfig
 * @Description : Mybatis DB 설정을 위한 클래스
 * @Modification Information
 * @ 
 * @  수정일             수정자               수정내용
 * @ ----------   ---------   -------------------------------                
 * @ 2020.07.10    GEONLEE    최초생성
 * 
 * @author geonlee
 * @version 1.0.0
 * @since 2020-07-10
 */

@Configuration
@MapperScan(
        basePackages = {"com.example.mapper"},
        sqlSessionFactoryRef = "sqlSessionFactory"
)
public class MybatisSimpleConfig {
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource, ApplicationContext applicationContext) throws Exception {
        SqlSessionFactoryBean factory = new SqlSessionFactoryBean();
        factory.setDataSource(dataSource); //classpath*:/mapper/*.xml
        factory.setMapperLocations(applicationContext.getResources("classpath*:/mapper/*.xml"));
        factory.setTypeAliasesPackage("com.example.vo");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setJdbcTypeForNull(null);
        factory.setConfiguration(configuration);
        return factory.getObject();
    }
}