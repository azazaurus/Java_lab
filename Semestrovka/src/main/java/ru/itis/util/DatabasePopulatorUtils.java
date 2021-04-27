package ru.itis.util;

import org.springframework.core.io.*;
import org.springframework.jdbc.datasource.init.*;

import javax.sql.*;

public abstract class DatabasePopulatorUtils {
	public static ResourceLoader resourceLoader = new DefaultResourceLoader();

	public static void addScript(ResourceDatabasePopulator populator, String scriptResourceLocation) {
		populator.addScript(resourceLoader.getResource(scriptResourceLocation));
	}

	public static void populate(DatabasePopulator populator, DataSource dataSource) {
		org.springframework.jdbc.datasource.init.DatabasePopulatorUtils.execute(populator, dataSource);
	}
}
