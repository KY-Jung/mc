package kr.gainsys.mc.config;

import java.io.IOException;
import java.util.Locale;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;

@Configuration
public class MessageConfig implements WebMvcConfigurer {
//public class MessageConfig {

	////////////////////////////////////////////////////////////////////////////////
	// 기본 설정
	
    @Bean
    // 스프링이 작성한 언어 리소스들을 사용할 수 있게 등록/설정하는 클래스
    public MessageSource messageSource() throws IOException {
    	ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasenames("classpath:/messages/message");
        messageSource.setDefaultEncoding("UTF-8");
        // locale에 해당하는 file이 없을 경우 system locale을 사용
        messageSource.setFallbackToSystemLocale(false);
        messageSource.setCacheSeconds(10 * 60); // 리로드 시간
        
        return messageSource;
    }
    
    @Bean
    public MessageSourceAccessor messageSourceAccessor() throws IOException {
        return new MessageSourceAccessor(messageSource());
    }
	////////////////////////////////////////////////////////////////////////////////
    
    
	////////////////////////////////////////////////////////////////////////////////
    // 추가 설정
    
    @Bean
    // 값이 없을 경우에는 기본값(현재 동작하는 컴퓨터 환경의 Locale)을 사용하도록 설정
    public LocaleResolver localeResolver() {

        CookieLocaleResolver resolver = new CookieLocaleResolver();
        resolver.setDefaultLocale(Locale.getDefault());
        //resolver.setCookieName("lang");
        return resolver;
    }
	
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
    @Bean
    // url의 query parameter에 지정한 값이 들어올 때 동작 ex) http://localhost:8080?lang=ko
    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
        interceptor.setParamName("lang");
        return interceptor;
    }    
	////////////////////////////////////////////////////////////////////////////////
    
}
