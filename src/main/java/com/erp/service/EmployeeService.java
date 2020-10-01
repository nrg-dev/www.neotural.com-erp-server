package com.erp.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.bo.ErpBo;
import com.erp.dto.EmployeeDto;
import com.erp.mongo.dal.EmployeeDAL;
import com.erp.mongo.dal.RandomNumberDAL;
import com.erp.mongo.model.AbsentList;
import com.erp.mongo.model.ContractList;
import com.erp.mongo.model.DailyReport;
import com.erp.mongo.model.Employee;
import com.erp.mongo.model.RandomNumber;
import com.erp.util.Custom;

@RestController
@RequestMapping(value = "/employee")
public class EmployeeService implements Filter {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	@Autowired
	ErpBo investmentBo;
	
	@Value("${noimage.base64}")
	private String noimagebase64;

	private final EmployeeDAL employeedal;
	private final RandomNumberDAL randomnumberdal;
	Employee employee=null;

	public EmployeeService(EmployeeDAL employeedal, RandomNumberDAL randomnumberdal) {
		this.employeedal = employeedal;
		this.randomnumberdal = randomnumberdal;
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {

		HttpServletResponse response = (HttpServletResponse) res;
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Methods", "POST,PUT, GET, OPTIONS, DELETE");
		response.setHeader("Access-Control-Max-Age", "3600");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Accept, X-Requested-With, remember-me");
		chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig filterConfig) {
	}

	@Override
	public void destroy() {
	}

	// Save & Update
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> save(@RequestBody Employee employee) {
		logger.info("save");
		RandomNumber randomnumber = null;
		boolean status;
		int temp=0;
		try {
			if(employee.getEmployeecode()!=null) {
				logger.info("update employee");
				temp=1;
				status = employeedal.save(employee,temp);
			}
			else {
				logger.info("save employee");
				randomnumber = randomnumberdal.getEmployeeRandamNumber();
				String employeecode = randomnumber.getCode() + randomnumber.getNumber();
				logger.debug("Employee code-->" + employeecode);
				employee.setEmployeecode(employeecode);
				employee.setAddeddate(Custom.getCurrentInvoiceDate());
				employee.setStatus("Active");
				logger.debug("Current Date-->" + Custom.getCurrentDate());
				temp=2;
				
				logger.debug("Employee Image Base64 -->"+employee.getProfilepic());
				if(employee.getProfilepic()!=null) {
					logger.info("Employee Image not null"); 
				}else {
					logger.info("Employee Image Null");
					employee.setProfilepic(noimagebase64);
				}
				
				status = employeedal.save(employee,temp);
				randomnumberdal.updateEmployeeRandamNumber(randomnumber);
			}

			if(status) {
				return new ResponseEntity<>(HttpStatus.OK); 

			}else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 

			}
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// load
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public ResponseEntity<?> load() {
		logger.info("load");
		List<Employee> responseList = null;
		try {
			responseList = employeedal.load(responseList);
			logger.info("Employee List Size-->"+responseList.size());
			return new ResponseEntity<List<Employee>>(responseList, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	    // load
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/getEmployeeTotalRowCount", method = RequestMethod.GET)
		public ResponseEntity<?> getEmployeeTotalRowCount() {
			logger.info("getEmployeeTotalRowCount");
			try {
				Integer employeeTotalRowCount = employeedal.getEmployeeTotalRowCount();
				logger.info("Employee Total Row Count-->"+employeeTotalRowCount);
				return new ResponseEntity<Integer>(employeeTotalRowCount, HttpStatus.CREATED);
			} catch (Exception e) {
				logger.error("Exception-->" + e.getMessage());
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			} finally {

			}
		}
		
	// get
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> get(String employeecode) {
		logger.info("get employee");
		List<Employee> responseList = null;
		try {
			responseList = employeedal.get(employeecode);
			logger.info("Base 64 Employee-->"+responseList.get(0).getProfilepic());
			return new ResponseEntity<List<Employee>>(responseList, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} finally {

		}
	}

	// get & load daily report
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loadDailyReport", method = RequestMethod.GET)
	public ResponseEntity<?> loadDailyReport(String employeecode,String date,String type) {
		logger.info("loadDailyReport");
		logger.debug("Employee Id-->"+employeecode);
		logger.debug("Date -->"+date);
		List<DailyReport> responseList = null;
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat your_format = new SimpleDateFormat("dd/MMM/yyyy");
		try {
			logger.debug("Report Date-->" + date);
			Date dt1 = format.parse(date);
			String reportdate = your_format.format(dt1);
			logger.debug("Change FormatedDate-->" + reportdate);
			
			responseList = employeedal.loadDailyReport(employeecode,reportdate,type);
			logger.debug("List Size-->"+responseList.size());
			return new ResponseEntity<List<DailyReport>>(responseList, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} finally {

		}
	}
	
	// get & load daily report
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loadTodayReport", method = RequestMethod.GET)
	public ResponseEntity<?> loadTodayReport(String employeecode,String date,String type) {
		logger.info("loadTodayReport");
		logger.debug("Employee Id-->"+employeecode);
		logger.debug("Date -->"+date); 
		List<DailyReport> responseList = null;
		try {
			responseList = employeedal.loadDailyReport(employeecode,date,type);
			logger.debug("List Size-->"+responseList.size());
			return new ResponseEntity<List<DailyReport>>(responseList, HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} finally {

		}
	}
		// get & load AbsentList
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/loadAbsentList", method = RequestMethod.GET)
		public ResponseEntity<?> loadAbsentList(String employeecode,String date,String type) {
			logger.info("loadAbsentList");
			logger.debug("EmployeeCode-->"+employeecode);
			logger.debug("Date -->"+date);
			List<AbsentList> responseList = null;
			try {
				responseList = employeedal.loadAbsentList(employeecode,date,type);
				logger.debug("List Size-->"+responseList.size());
				return new ResponseEntity<List<AbsentList>>(responseList, HttpStatus.OK);

			} catch (Exception e) {
				logger.error("Exception-->" + e.getMessage());
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			} finally {

			}
		}
		
		// get & load ContractList
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/loadContractList", method = RequestMethod.GET)
		public ResponseEntity<?> loadContractList(String employeecode) {
			logger.info("loadContractList");
			logger.debug("Employee Id-->"+employeecode);
			List<ContractList> responseList = null;
			try {
				responseList = employeedal.loadContractList(employeecode);
				logger.debug("List Size-->"+responseList.size());
				return new ResponseEntity<List<ContractList>>(responseList, HttpStatus.OK);

			} catch (Exception e) {
				logger.error("Exception-->" + e.getMessage());
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

			} finally {

			}
		}
		
	// Remove
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<?> remove(String employeecode) {
		logger.info("remove employee");
		try {
			employee = new Employee();
			logger.info("Before Calling  remove employee");
			employeedal.remove(employeecode);
			logger.info("Successfully Called  remo employee");
			return new ResponseEntity<>(HttpStatus.OK); 

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}

	}
	
    // Save Daily Report
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/saveDailyReport", method = RequestMethod.POST)
	public ResponseEntity<?> saveDailyReport(@RequestBody EmployeeDto employeeDto) {
		logger.info("saveDailyReport");
		try {
			String[] res = employeeDto.getDate().split("/");
			String monthname = res[1];
			logger.debug("DailyReport Month Name-->" + monthname);
			String currentyear = Custom.getCurrentYear();
			System.out.println("DailyReport Current Year -->"+currentyear);
			employeeDto.setMonthname(monthname); 
			employeeDto.setYear(currentyear);
			boolean status = employeedal.saveUpdateDailyReport(employeeDto);
			if(status) {
				return new ResponseEntity<>(HttpStatus.OK); 
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
			}

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		} finally {

		}
	}
	
	// Save Daily Report
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/updateDailyReport", method = RequestMethod.PUT)
	public ResponseEntity<?> updateDailyReport(@RequestBody EmployeeDto employeeDto) {
		logger.info("updateDailyReport");
		try {
			logger.debug("Report Date-->" + employeeDto.getDate());
			boolean status = employeedal.saveUpdateDailyReport(employeeDto);
			if(status) {
				return new ResponseEntity<>(HttpStatus.OK); 
			} else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
			}

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
		} finally {

		}
	}
	
		// Save Absent
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/saveAbsent", method = RequestMethod.POST)
		public ResponseEntity<?> saveAbsent(@RequestBody EmployeeDto employeeDto) {
			logger.info("saveAbsent");
			try {
				String[] res = employeeDto.getDate().split("/");
				String monthname = res[1];
				logger.debug("AbsentReport Month Name-->" + monthname);
				String currentyear = Custom.getCurrentYear();
				System.out.println("AbsentReport CurrentYear -->"+currentyear);
				employeeDto.setMonthname(monthname); 
				employeeDto.setYear(currentyear); 
				boolean status = employeedal.saveAbsentList(employeeDto);
				if(status) {
					return new ResponseEntity<>(HttpStatus.OK); 
				} else {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
				}

			} catch (Exception e) {
				logger.error("Exception-->" + e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
			} finally {

			}
		}
		
		// Save Contract
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/saveContract", method = RequestMethod.POST)
		public ResponseEntity<?> saveContract(@RequestBody ContractList contractList) {
			logger.info("saveContract");
			try {
				boolean status = employeedal.saveContractList(contractList);
				if(status) {
					return new ResponseEntity<>(HttpStatus.OK); 
				} else {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
				}

			} catch (Exception e) {
				logger.error("Exception-->" + e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
			} finally {

			}
		}
				
		// Update Contract
		@CrossOrigin(origins = "http://localhost:8080")
		@RequestMapping(value = "/updateContract", method = RequestMethod.PUT)
		public ResponseEntity<?> updateContract(@RequestBody ContractList contractList) {
			logger.info("updateContract");
			try {
				boolean status = employeedal.updateContractList(contractList);
				if(status) {
					return new ResponseEntity<>(HttpStatus.OK); 
				} else {
					return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); 
				}

			} catch (Exception e) {
				logger.error("Exception-->" + e.getMessage());
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 500
			} finally {

			}
		}
				

}
