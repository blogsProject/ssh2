package com.luotianyi.ssh2.config;

import java.util.regex.Pattern;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.context.annotation.Import;
import org.springframework.core.type.filter.RegexPatternTypeFilter;

@Configuration
@Import(value = HibernateConfig.class)
@ComponentScan(basePackages = { "com.luotianyi.ssh2" }, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, value = SpringConfig.webPackage.class) })
public class SpringConfig {
	public static class webPackage extends RegexPatternTypeFilter {

		public webPackage() {
			super(Pattern.compile("com.luotianyi.ssh2.config.WEBInitializer"));
		}

	}
}
