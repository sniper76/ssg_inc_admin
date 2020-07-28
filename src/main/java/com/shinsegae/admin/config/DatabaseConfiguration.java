package com.shinsegae.admin.config;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.datasource.DataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class DatabaseConfiguration
{

	// 설정을 한 클래스에 몰아 넣기위해 클래스 안에 클래스를 만듬.
	@Configuration
	// 다수의 데이터 연결이 있다면, 규정상 매핑여부와 상관없이 한쪽 @Bean 의 접근자별 @Primary 를 강제로 지정해야함.
	@MapperScan
	(
		basePackages ="com.shinsegae.admin.mapper"
	)
	public static class Mybatis_PostgreSQL
	{
		@Autowired
		ApplicationContext applicationContext;

		@Bean
		public DataSource getDataSource() throws IOException
		{
			Properties prop = new Properties();
			prop.load(DataSourceFactory.class.getClassLoader().getResourceAsStream("hikari_pgsql.properties"));
			return new HikariDataSource(new HikariConfig(prop));
		}

		@Bean
		public SqlSessionFactory getSqlSessionFactory() throws Exception
		{
			SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
			sqlSessionFactoryBean.setDataSource(getDataSource());
			// xml 매핑을 위한 것임으로 xml을 통한 매핑을 사용하지 않는다면 생략가능.
			// 밑에 xml 예제도 사용할 것임으로 설정
			sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:mapper/*.xml"));
			return sqlSessionFactoryBean.getObject();
		}

		@Bean
		public SqlSessionTemplate getSqlSessionTemplate() throws Exception
		{
			return new SqlSessionTemplate(getSqlSessionFactory());
		}
	}
}