package com.luotianyi.ssh2.config;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.orm.hibernate5.support.OpenSessionInViewInterceptor;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@EnableTransactionManagement
@PropertySource(value = { "classpath:config.properties" })
public class HibernateConfig {

	@Autowired
	Environment dataConfig;

	@SuppressWarnings("deprecation")
	@Bean(name = "dataSource")
	public DataSource getDataSource() throws SQLException {
		DruidDataSource dataSource = new DruidDataSource();
		dataConfig.getProperty("dirver");
		dataSource.setDriverClassName(dataConfig.getProperty("dirver"));
		dataSource.setUrl(dataConfig.getProperty("url"));
		dataSource.setUsername(dataConfig.getProperty("dbusername"));
		dataSource.setPassword(dataConfig.getProperty("dbpassword"));
		dataSource.setInitialSize(Integer.parseInt(dataConfig.getProperty("initialSize")));
		dataSource.setMaxActive(Integer.parseInt(dataConfig.getProperty("maxActive")));
		dataSource.setMaxIdle(Integer.parseInt(dataConfig.getProperty("maxIdle")));
		dataSource.setMinIdle(Integer.parseInt(dataConfig.getProperty("minIdle")));
		dataSource.setMaxWait(Long.parseLong(dataConfig.getProperty("maxWait")));
		dataSource.setRemoveAbandoned(Boolean.parseBoolean(dataConfig.getProperty("removeAbandoned")));
		dataSource.setRemoveAbandonedTimeout(Integer.parseInt(dataConfig.getProperty("removeAbandonedTimeout")));
		dataSource.setTimeBetweenEvictionRunsMillis(
				Long.parseLong(dataConfig.getProperty("timeBetweenEvictionRunsMillis")));
		dataSource.setMinEvictableIdleTimeMillis(Long.parseLong(dataConfig.getProperty("minEvictableIdleTimeMillis")));
		dataSource.setValidationQuery(dataConfig.getProperty("validationQuery"));
		dataSource.setTestWhileIdle(Boolean.parseBoolean(dataConfig.getProperty("testWhileIdle")));
		dataSource.setTestOnBorrow(Boolean.parseBoolean(dataConfig.getProperty("testOnBorrow")));
		dataSource.setTestOnReturn(Boolean.parseBoolean(dataConfig.getProperty("testOnReturn")));
		dataSource.setPoolPreparedStatements(Boolean.parseBoolean(dataConfig.getProperty("poolPreparedStatements")));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(
				Integer.parseInt(dataConfig.getProperty("maxPoolPreparedStatementPerConnectionSize")));
		dataSource.setFilters(dataConfig.getProperty("filters"));
		return dataSource;
	}

	@Bean("sessionFactory")
	public LocalSessionFactoryBean sessionFactory(DataSource dataSource) throws FileNotFoundException, IOException {
		LocalSessionFactoryBean bean = new LocalSessionFactoryBean();
		bean.setDataSource(dataSource);
		bean.setPackagesToScan("com.luotianyi.ssh2.entiy");
		Properties properties = new Properties();
		properties.load(new BufferedInputStream(new FileInputStream("src/main/resources/hibernateconfig.properties")));
		bean.setHibernateProperties(properties);
		return bean;
	}

	@Bean("transactionManager")
	public HibernateTransactionManager transactionManager(SessionFactory sessionFactory) {
		return new HibernateTransactionManager(sessionFactory);
	}

	@Bean("openSession")
	public OpenSessionInViewInterceptor openSessionInViewInterceptor(SessionFactory sessionFactory) {
		OpenSessionInViewInterceptor inViewInterceptor = new OpenSessionInViewInterceptor();
		inViewInterceptor.setSessionFactory(sessionFactory);
		return inViewInterceptor;
	}
}
