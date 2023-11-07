package com.srtech.service;

import java.sql.SQLException;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.srtech.entity.Category;

@Service
@Transactional
public class CatalogService {

	@Transactional(rollbackFor = SQLException.class,propagation = Propagation.MANDATORY)
	public void updateRecord(Category entity) {
		
		ma();
	}

	@Transactional(rollbackFor = SQLException.class,propagation = Propagation.REQUIRES_NEW)
	private void ma() {
		
	}

	@Transactional(readOnly = true, timeout = 1000)
	public void getSearchRecords() {

	}

}
