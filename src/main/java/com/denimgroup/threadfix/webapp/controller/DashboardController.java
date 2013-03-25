////////////////////////////////////////////////////////////////////////
//
//     Copyright (c) 2009-2013 Denim Group, Ltd.
//
//     The contents of this file are subject to the Mozilla Public License
//     Version 2.0 (the "License"); you may not use this file except in
//     compliance with the License. You may obtain a copy of the License at
//     http://www.mozilla.org/MPL/
//
//     Software distributed under the License is distributed on an "AS IS"
//     basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
//     License for the specific language governing rights and limitations
//     under the License.
//
//     The Original Code is ThreadFix.
//
//     The Initial Developer of the Original Code is Denim Group, Ltd.
//     Portions created by Denim Group, Ltd. are Copyright (C)
//     Denim Group, Ltd. All Rights Reserved.
//
//     Contributor(s): Denim Group, Ltd.
//
////////////////////////////////////////////////////////////////////////
package com.denimgroup.threadfix.webapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.denimgroup.threadfix.service.ApplicationService;
import com.denimgroup.threadfix.service.PermissionService;
import com.denimgroup.threadfix.service.SanitizedLogger;
import com.denimgroup.threadfix.service.ScanService;
import com.denimgroup.threadfix.service.VulnerabilityCommentService;
import com.denimgroup.threadfix.service.report.ReportsService;

/**
 * @author bbeverly
 * @author mcollins
 * 
 */
@Controller
@RequestMapping("/dashboard")
public class DashboardController {
	
	public DashboardController(){}
	
	private VulnerabilityCommentService vulnerabilityCommentService;
	private ScanService scanService;

	@Autowired
	public DashboardController(ScanService scanService,
			ReportsService reportsService,
			PermissionService permissionService,
			ApplicationService applicationService,
			VulnerabilityCommentService vulnerabilityCommentService){
		this.vulnerabilityCommentService = vulnerabilityCommentService;
		this.scanService = scanService;
	}
	
	private final SanitizedLogger log = new SanitizedLogger(OrganizationsController.class);

	@RequestMapping(method = RequestMethod.GET)
	public String index(Model model, HttpServletRequest request, HttpServletResponse response) {
		log.info("Hit the dashboard");
		
		model.addAttribute("recentComments", vulnerabilityCommentService.loadMostRecent());
		model.addAttribute("recentScans", scanService.loadMostRecent());
		
		return "dashboard/dashboard";
	}
	
}
