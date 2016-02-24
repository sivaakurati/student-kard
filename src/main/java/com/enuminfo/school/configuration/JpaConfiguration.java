/**
 * 
 */
package com.enuminfo.school.configuration;

import java.io.IOException;
import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Kumar
 */
@Configuration
@EnableJpaAuditing
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.enuminfo.school.hibernate")
public class JpaConfiguration {
	
	@Autowired DataSource dataSource;
	private static final String PACKAGES_TO_SCAN = "com.enuminfo.school.hibernate";

	public @Bean Properties hibernateProperties() {
		try {
			Properties properties = new Properties();
			properties.load(new ClassPathResource("application.properties").getInputStream());
			return properties;
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	public @Bean LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean localContainerEntityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		localContainerEntityManagerFactoryBean.setDataSource(dataSource);
		localContainerEntityManagerFactoryBean.setPersistenceProviderClass(HibernatePersistenceProvider.class);
		localContainerEntityManagerFactoryBean.setPackagesToScan(PACKAGES_TO_SCAN);
		localContainerEntityManagerFactoryBean.setJpaProperties(hibernateProperties());
		return localContainerEntityManagerFactoryBean;
	}
}
