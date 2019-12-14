package org.zerock.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@ComponentScan(basePackages = {"org.zerock"})
@MapperScan(basePackages = {"org.zerock.mapper"})
//Spring 에 관련된 설정. POJO. Java 영역 설정
public class RootConfig {
	
	@Bean
	public DataSource dataSource() {
		HikariConfig hikariConfig = new HikariConfig();
		//hikariConfig.setDriverClassName("com.mysql.jdbc.Driver");
		//hikariConfig.setJdbcUrl("jdbc:mysql://localhost:3306/spring?characterEncoding=utf8&useSSL=false");
		// log4jdbc-log4j2 설정
		hikariConfig.setDriverClassName("net.sf.log4jdbc.sql.jdbcapi.DriverSpy");
		hikariConfig.setJdbcUrl("jdbc:log4jdbc:mysql://localhost:3306/spring?characterEncoding=utf8&useSSL=false");
		hikariConfig.setUsername("gugu");
		hikariConfig.setPassword("1234");
		
		return new HikariDataSource(hikariConfig);
	}
	
    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
        sqlSessionFactory.setDataSource(dataSource());
        return sqlSessionFactory.getObject();
    }
}
