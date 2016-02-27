/**
 * 
 */
package com.enuminfo.student.configuration;

import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;
import org.thymeleaf.templateresolver.TemplateResolver;

/**
 * @author Kumar
 */
public class ThymeleafConfiguration {

	public @Bean ServletContextTemplateResolver templateResolver() {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        templateResolver.setCacheable(false);
        return templateResolver;
    }
    
    public @Bean SpringTemplateEngine templateEngine() {
        final SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        final Set<TemplateResolver> templateResolveres = new HashSet<TemplateResolver>();
        templateResolveres.add(templateResolver());
        templateEngine.setTemplateResolvers(templateResolveres);
        return templateEngine;
    }
    
    public @Bean ThymeleafViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        return viewResolver;
    }
}
