package tn.enis.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

@Configuration
public class MessageConfig {
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		// Nom des fichiers properties (sans extension)
		messageSource.setBasenames("messages", "validation", "errors");
		// Encodage des fichiers
		messageSource.setDefaultEncoding("UTF-8");
		// Cache pour les messages (en millisecondes)
		messageSource.setCacheSeconds(3600);
		// Utiliser le code comme message par défaut si non trouvé
		messageSource.setUseCodeAsDefaultMessage(true);
		// Locale par défaut
		messageSource.setDefaultLocale(Locale.ENGLISH);
		return messageSource;
	}

}