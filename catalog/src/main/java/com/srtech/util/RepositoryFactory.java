package com.srtech.util;

import org.springframework.data.repository.Repository;

public class RepositoryFactory<T> {

	public Repository getRepository(String reposirotyName) {

		switch (reposirotyName) {
		case "SKU": {

		}
		case "PRODUCT": {

		}
		case "CATEGORY": {

		}
		default:
			throw new IllegalArgumentException("Invlid repository name "+reposirotyName);
		}
	}
}
