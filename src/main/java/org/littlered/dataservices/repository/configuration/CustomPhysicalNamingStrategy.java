package org.littlered.dataservices.repository.configuration;

import org.littlered.dataservices.DataservicesApplication;
import org.hibernate.boot.model.naming.Identifier;
import org.hibernate.boot.model.naming.PhysicalNamingStrategy;
import org.hibernate.engine.jdbc.env.spi.JdbcEnvironment;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;

@Configuration
@Component
public class CustomPhysicalNamingStrategy implements PhysicalNamingStrategy, Serializable {

	public static final CustomPhysicalNamingStrategy INSTANCE = new CustomPhysicalNamingStrategy();

	public CustomPhysicalNamingStrategy() {
		Properties props = new Properties();
		try {
			InputStream inputStream;
			inputStream = DataservicesApplication.class.getClassLoader().getResourceAsStream("application.properties");
			if (inputStream == null) {
				inputStream = new FileInputStream("application.properties");
			}
			props.load(inputStream);
		} catch (Exception e) {
			e.printStackTrace();
		}
		tablePrefix = (String) props.get("db.table_prefix");
	}

	private final String tablePrefix;

	@Override
	public Identifier toPhysicalCatalogName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		return convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalColumnName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		return convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalSchemaName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		return convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalSequenceName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		return convertToSnakeCase(identifier);
	}

	@Override
	public Identifier toPhysicalTableName(final Identifier identifier, final JdbcEnvironment jdbcEnv) {
		return convertTableToSnakeCase(identifier);
	}

	private Identifier convertToSnakeCase(final Identifier identifier) {
		if (identifier == null) {
			return null;
		}
		final String regex = "([a-z])([A-Z])";
		final String replacement = "$1_$2";
		final String newName = identifier.getText()
				.replaceAll(regex, replacement)
				.toLowerCase();

		return Identifier.toIdentifier(newName);
	}

	private Identifier convertTableToSnakeCase(final Identifier identifier) {
		final String regex = "([a-z])([A-Z])";
		final String replacement = "$1_$2";
		String prefix = tablePrefix;
		if (identifier.getText().startsWith("Bbc") || identifier.getText().startsWith("bbc_")) {
			prefix = "";
		}
		final String newName = (prefix.concat(identifier.getText()))
				.replaceAll(regex, replacement)
				.toLowerCase();

		return Identifier.toIdentifier(newName);
	}
}
