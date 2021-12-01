package com.etiya.rentACarSpring.core.utilities.businnessRules;

import com.etiya.rentACarSpring.core.utilities.results.Result;

public class BusinnessRules {
	public static Result run(Result ...logics) {
		for (Result logic : logics) {
			if (!logic.isSuccess()) {
				return logic;
			}
		}
		return null;
	}
}
