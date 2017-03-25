package io.github.agileluo.smartroute.route;

import io.github.agileluo.smartroute.RouteRequest;

public interface Route extends Comparable<Route> {
	void route(RouteRequest req);
}
