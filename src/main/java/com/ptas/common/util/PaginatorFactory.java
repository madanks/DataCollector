/*
 * Copyright 2014, Verisk Health, Inc. and/or its affiliates. All rights reserved.
 * VERISK HEALTH PROPRIETARY/CONFIDENTIAL.
 *
 */
package com.ptas.common.util;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.ServletRequestUtils;

public class PaginatorFactory {

	public static Paginator getPaginator(HttpServletRequest request) {
		int currentPage = ServletRequestUtils.getIntParameter(request, "p", 0);
		int pageSize = ServletRequestUtils.getIntParameter(request, "s", 20);
		Paginator paginator = new Paginator();
		paginator.setCurrentPage(currentPage);
		paginator.setNumberOfRecordsToFetch(pageSize);
		return paginator;
	}
}
