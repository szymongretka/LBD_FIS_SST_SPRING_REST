package pl.fis.szymon.gretka.config;

import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

@Configuration
public class LocaleConfig implements WebMvcConfigurer {

	@Bean
	public MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	     
	    messageSource.setBasename("classpath:messages");
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
	
	@Bean
	public LocalValidatorFactoryBean getValidator() {
	    LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
	    bean.setValidationMessageSource(messageSource());
	    return bean;
	}
	
	
	@Bean
	public LocaleResolver localeResolver() {
	    SessionLocaleResolver slr = new SessionLocaleResolver();
	    //slr.setDefaultLocale(Locale.getDefault());
	    return slr;
	}
	
	@Bean //add an interceptor bean that will switch to a new locale based on the value of the lang parameter appended to a request
	public LocaleChangeInterceptor localeChangeInterceptor() {
	    LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
	    lci.setParamName("lang");
	    return lci;
	}
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	    registry.addInterceptor(localeChangeInterceptor());
	}
	
}
