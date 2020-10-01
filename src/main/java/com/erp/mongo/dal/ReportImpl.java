package com.erp.mongo.dal;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.erp.dto.EmployeeDto;
import com.erp.mongo.model.DailyReport;
import com.erp.mongo.model.Employee;
import com.erp.mongo.model.POInvoice;
import com.erp.mongo.model.SOInvoice;
import com.erp.util.Custom;

@Repository
public class ReportImpl implements ReportDAL {

	public static final Logger logger = LoggerFactory.getLogger(ReportImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	// employee load
	public List<Employee> employeeReport(List<Employee> employeelist) {
		logger.info("employeeReport");
		employeelist = mongoTemplate.findAll(Employee.class);
		return employeelist;

	}

	// purchase load
	public List<POInvoice> purchaseReport(List<POInvoice> purchaselist) {
		logger.info("purchaseReport");
		purchaselist = mongoTemplate.findAll(POInvoice.class);
		return purchaselist;

	}

	// sales load
	public List<SOInvoice> salesReport(List<SOInvoice> saleslist) {
		logger.info("salesReport");
		saleslist = mongoTemplate.findAll(SOInvoice.class);
		return saleslist;
	}

	public List<DailyReport> loadEmpDailyReport(List<DailyReport> dailyreportlist, EmployeeDto emprep) {
		Query query = new Query();
		if(emprep.getReporttype().equalsIgnoreCase("monthlyreport")) {
			logger.info("Monthly Report");
			String currentyear = Custom.getCurrentYear();
			logger.debug("CurrentYear -->"+currentyear+"MonthName -->"+emprep.getMonthname());
			
			query.addCriteria(Criteria.where("employeecode").is(emprep.getId()));
			query.addCriteria(Criteria.where("monthname").is(emprep.getMonthname()));
			query.addCriteria(Criteria.where("year").is(currentyear)); 
			dailyreportlist = mongoTemplate.find(query,DailyReport.class);
		}else {
			logger.info("DAoImpl Custom Report");
			if(emprep.getFromdate().equalsIgnoreCase(emprep.getTodate())) {
				logger.info("-------- Both Dates are Equal --------");
				query.addCriteria(Criteria.where("employeecode").is(emprep.getId()));
				query.addCriteria(Criteria.where("date").is(emprep.getFromdate()));
				dailyreportlist = mongoTemplate.find(query,DailyReport.class);
			}else {
				logger.info("-------- Both Dates are Not Equal --------");
				query.addCriteria(Criteria.where("employeecode").is(emprep.getId()));
				query.addCriteria(Criteria.where("date").gte(emprep.getFromdate()).lte(emprep.getTodate()));
				dailyreportlist = mongoTemplate.find(query,DailyReport.class);
			}
		}
			
		logger.debug("Daily ReportList Size -->"+dailyreportlist.size()); 
		return dailyreportlist;
	}
}
