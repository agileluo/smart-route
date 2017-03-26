package io.github.agileluo.smartroute.parser;

import java.util.Properties;

import io.github.agileluo.smartroute.route.Route;

public interface RouteParse {
	boolean canParse(String key, String val, Properties p);
	Route parse(String key, String val, Properties p);
}
