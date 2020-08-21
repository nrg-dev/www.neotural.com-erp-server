package com.erp.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import org.springframework.beans.factory.annotation.Autowire;

//import javax.enterprise.inject.Produces;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.mongo.dal.RandomNumberDAL;
import com.erp.mongo.dal.UserMgtDAL;
import com.erp.mongo.model.Menu;
import com.erp.mongo.model.RandomNumber;
import com.erp.mongo.model.UserRole;

@SpringBootApplication
@RestController
@RequestMapping(value = "/userMgt")
public class UserMgtService implements Filter {

	public static final Logger logger = LoggerFactory.getLogger(UserMgtService.class);
	UserRole userrole = null;
	private final UserMgtDAL usermgtdal;
	private final RandomNumberDAL randomnumberdal;

	public UserMgtService(UserMgtDAL usermgtdal,RandomNumberDAL randomnumberdal) {
		this.usermgtdal = usermgtdal;
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

	// Load customer / vendor name for populate for auto text box
	/* @CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loaddepartmentname", method = RequestMethod.GET)
	public ResponseEntity<?> loaddepartmentname() {
		logger.info("loaddepartmentname");
		ArrayList<String> departmentlist = null;
		try {
			logger.info("Before Calling Load departmentname list");
			//departmentlist = usermgtdal.loadCustomerVendorName();
			logger.info("Successfully Called Load departmentname list");
			return new ResponseEntity<ArrayList<String>>(departmentlist, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	} */

	// save petty cash
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseEntity<?> saveuserMgmt(@RequestBody String menuarray) {
		logger.info("saveuserMgmt");
		RandomNumber randomnumber = null;
		int randomId=21;
		UserRole user = new UserRole();
		try {
			logger.debug("Mapped value -->" + menuarray);
			randomnumber = randomnumberdal.getRandamNumber(randomId);
			String invoice = randomnumber.getCode() + randomnumber.getNumber();
			logger.debug("Invoice number -->" + invoice);
			 
			String string = menuarray;
			String userarray = StringUtils.removeStart(StringUtils.removeEnd(string, "]"), "[");
			 
			JSONObject jObject = new JSONObject(userarray);

			user.setInvnumber(invoice);
			user.setUsername(jObject.getString("username"));
		 	user.setPassword(jObject.getString("password"));
		 	user.setDepartmentname(jObject.getString("departmentname"));
		 	user.setMenuItem(jObject.getString("menuArray"));
		 	
		 	logger.debug("UserName-->"+user.getUsername());
		 	logger.debug("Password-->"+user.getPassword());
		 	logger.debug("Department-->"+user.getDepartmentname());
		 	
		 	user.setMenuItem1(jObject.getString("menuItem1"));
		 	user.setMenuItem2(jObject.getString("menuItem2"));
		 	user.setMenuItem3(jObject.getString("menuItem3"));
		 	
		 	user.setMenuItem4(jObject.getString("menuItem4"));
		 	user.setPurchasesubmenu(jObject.getString("purchasesubmenu"));

		 	user.setMenuItem5(jObject.getString("menuItem5"));
		 	user.setProductsubmenu(jObject.getString("productsubmenu"));

		 	user.setMenuItem6(jObject.getString("menuItem6"));
		 	user.setSalessubmenu(jObject.getString("salessubmenu"));

		 	user.setMenuItem7(jObject.getString("menuItem7"));
		 	user.setMenuItem8(jObject.getString("menuItem8"));
		 	user.setStatus("Active");
		 	
		 	logger.debug("Menu1 -->"+user.getMenuItem1()); 
		 	logger.debug("Menu2-->"+user.getMenuItem2());
		 	 
		 	usermgtdal.save(user);
			randomnumberdal.updateRandamNumber(randomnumber,randomId);
			 
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// Load petty cash data
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/load", method = RequestMethod.GET)
	public ResponseEntity<?> load() {
		logger.info("load");
		List<UserRole> userlist = null;
		try {
			logger.info("Before Calling load user list");
			userlist = usermgtdal.load(userlist);
			logger.info("Successfully Called load user list");
			return new ResponseEntity<List<UserRole>>(userlist, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// Remove
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<?> remove(String id) {
		try {
			logger.debug("Remove -->" + id);
			userrole = new UserRole();
			logger.info("Before Calling remove user");
			usermgtdal.removeUser(id);
			logger.info("Successfully Called remove user");
			return new ResponseEntity<>(HttpStatus.OK);

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} finally {

		}
	}
	
	// get Menu
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/getMenuName", method = RequestMethod.GET)
	public ResponseEntity<?> getMenuName(String menuCode) {
		logger.info("getMenuName");
		List<Menu> menulist = null;
		try {
			menulist = usermgtdal.getMenuName(menuCode);
			return new ResponseEntity<List<Menu>>(menulist, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} finally {

		}
	}
	
	// get SubMenu
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/removeUser", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeUser(String id) {
		logger.info("removeUser");
		try {
			logger.debug("Remove UserID-->" + id);
			usermgtdal.removeUser(id);
			
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}


}
