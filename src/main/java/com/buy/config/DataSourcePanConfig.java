package com.buy.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import javax.sql.DataSource;

/**
 * @author SXD
 * @date 2017/12/04
 * 多数据源装载配置类
 * 数据源的声明
 */
@Configuration
@MapperScan(basePackages = {"com.buy.dao.pan"}, sqlSessionTemplateRef = "panSqlSessionTemplate")
public class DataSourcePanConfig {

    @Bean(name = "panDataSource")
    @Primary
    @ConfigurationProperties(prefix = "datasource.pan")
    public DataSource panDataSource(){
        return DataSourceBuilder.create().build();
    }

    @Bean
    public SqlSessionFactory panSqlSessionFactory(@Qualifier("panDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            bean.setMapperLocations(resolver.getResources("classpath*:mapping/pan/*.xml"));
            return bean.getObject();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SqlSessionTemplate panSqlSessionTemplate(@Qualifier("panSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory);
        return template;
    }
}
