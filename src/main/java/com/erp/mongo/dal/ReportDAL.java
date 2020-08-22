package com.erp.mongo.dal;

import java.util.List;

import com.erp.dto.EmployeeDto;
import com.erp.mongo.model.DailyReport;
import com.erp.mongo.model.Employee;
import com.erp.mongo.model.POInvoice;
import com.erp.mongo.model.SOInvoice;

public interface ReportDAL {

	public List<Employee> employeeReport(List<Employee> employeelist);

	public List<POInvoice> purchaseReport(List<POInvoice> purchaselist);

	public List<SOInvoice> salesReport(List<SOInvoice> saleslist);

	public List<DailyReport> loadEmpDailyReport(List<DailyReport> dailyreportlist, EmployeeDto emprep);   

}