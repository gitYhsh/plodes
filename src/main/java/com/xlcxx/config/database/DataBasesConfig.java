package com.xlcxx.config.database;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Description: plodes
 * Created by yhsh on 2020/3/19 9:22
 * version 2.0
 * 方法说明
 */
@Configuration
public class DataBasesConfig {

	private static final Logger logger = LoggerFactory.getLogger(DataBasesConfig.class);

	/*
	 * 数据库配置
	 * */
	@Value("${spring.datasource.url}")
	private String dbUrl;
	@Value("${spring.datasource.username}")
	private String userName;
	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.driverClassName}")
	private String driverClassName;

	//druid配置信息
	@Value("${spring.datasource.initialSize}")
	private int initialSize;

	@Value("${spring.datasource.minIdle}")
	private int minIdle;

	@Value("${spring.datasource.maxActive}")
	private int maxActive;

	@Value("${spring.datasource.maxWait}")
	private int maxWait;

	@Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
	private int timeBetweenEvictionRunsMillis;

	@Value("${spring.datasource.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${spring.datasource.validationQuery}")
	private String validationQuery;

	@Value("${spring.datasource.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${spring.datasource.testOnBorrow}")
	private boolean testOnBorrow;

	@Value("${spring.datasource.testOnReturn}")
	private boolean testOnReturn;

	@Value("${spring.datasource.poolPreparedStatements}")
	private boolean poolPreparedStatements;

	@Value("${spring.datasource.maxPoolPreparedStatementPerConnectionSize}")
	private int maxPoolPreparedStatementPerConnectionSize;

	@Value("${spring.datasource.filters}")
	private String filters;

	@Value("${spring.datasource.connectionProperties}")
	private String connectionProperties;

	@Bean
	@Primary
	public DataSource dataSource() {

		DruidDataSource datasource = new DruidDataSource();
		datasource.setMinIdle(minIdle);
		datasource.setInitialSize(initialSize);
		datasource.setMaxWait(maxWait);
		datasource.setMaxActive(maxActive);
		datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
		datasource.setValidationQuery(validationQuery);
		datasource.setTestWhileIdle(testWhileIdle);
		datasource.setTestOnBorrow(testOnBorrow);
		datasource.setTestOnReturn(testOnReturn);
		datasource.setPoolPreparedStatements(poolPreparedStatements);
		datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);

		try {
			datasource.setFilters(filters);
		} catch (SQLException e) {
			logger.error("druid configuration initialization filter : {0}", e);
		}
		datasource.setConnectionProperties(connectionProperties);
		datasource.setUrl(dbUrl);
		datasource.setUsername(userName);
		datasource.setPassword(password);
		datasource.setDriverClassName(driverClassName);

		return datasource;
	}

	@Bean
	@SuppressWarnings("unchecked")
	public ServletRegistrationBean druidStatViewServletBean() {
		//后台的路径
		ServletRegistrationBean statViewServletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
		Map<String,String> params = new HashMap<>();
		//账号密码，是否允许重置数据
		params.put("loginUsername","admin");
		params.put("loginPassword","admin");
		params.put("resetEnable","true");
		statViewServletRegistrationBean.setInitParameters(params);
		return statViewServletRegistrationBean;
	}
	/**
	 * 注册 filter
	 *
	 * @return
	 */
	@Bean
	public FilterRegistrationBean filterRegistrationBean() {
		FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
		filterRegistrationBean.setFilter(new WebStatFilter());
		filterRegistrationBean.addUrlPatterns("/*");
		filterRegistrationBean.addInitParameter("exclusions",
				"*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
		filterRegistrationBean.addInitParameter("profileEnable", "true");
		return filterRegistrationBean;
	}

	@Bean
	public StatFilter statFilter() {
		StatFilter statFilter = new StatFilter();
		//slowSqlMillis用来配置SQL慢的标准，执行时间超过slowSqlMillis的就是慢。
		statFilter.setLogSlowSql(true);
		//SQL合并配置
		statFilter.setMergeSql(true);
		//slowSqlMillis的缺省值为3000，也就是3秒。
		statFilter.setSlowSqlMillis(1000);
		return statFilter;
	}

	@Bean
	public WallFilter wallFilter() {
		WallFilter wallFilter = new WallFilter();
		//允许执行多条SQL
		WallConfig config = new WallConfig();
		config.setMultiStatementAllow(true);
		wallFilter.setConfig(config);
		return wallFilter;
	}

}
