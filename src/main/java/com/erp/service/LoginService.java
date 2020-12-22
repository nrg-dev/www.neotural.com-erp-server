package com.erp.service;

import java.io.IOException;
import java.util.ArrayList;
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

//import org.springframework.beans.factory.annotation.Autowire;

//import javax.enterprise.inject.Produces;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erp.bo.ErpBo;
import com.erp.dto.User;
import com.erp.mongo.model.Index;
import com.erp.mongo.model.RecentUpdates;
import com.erp.mongo.model.UserRole;

@RestController
@RequestMapping(value = "/auth")
public class LoginService implements Filter {

	public static final Logger logger = LoggerFactory.getLogger(LoginService.class);

	@Autowired
	ErpBo bo;

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

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<?> loginUser(@RequestParam String username, @RequestParam String password) {
		logger.info("loginUser");
		logger.debug("User Name-->" + username);
		logger.debug("Password-->" + password);
		User user = null;
		try {
			user = new User();
			user.setUsername(username);
			user.setPassword(password);
			user = bo.userLogin(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		} catch (Exception e) {
			user.setStatus("Network Error Please try again");
			logger.error("Exception-->" + e.getMessage());
		} finally {

		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	// load index data
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loadIndex", method = RequestMethod.GET)
	public ResponseEntity<?> loadIndex() {
		logger.info("loadIndex");
		List<Index> indexlist = null;
		try {
			logger.info("Before Calling ItemLoad");
			indexlist = new ArrayList<Index>();
			indexlist = bo.loadIndex(indexlist);
			logger.info("After Calling ItemLoad");
			logger.info("Index Size-->"+indexlist.size());
			return new ResponseEntity<List<Index>>(indexlist, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}
			

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/Checkuser", method = RequestMethod.GET)
	public ResponseEntity<?> Checkuser(@RequestParam String username) {
		logger.info("Checkuser");
		User user = null;
		try {
			logger.debug("User Name-->" + username);
			user = new User();
			user.setUsername(username);
			user = bo.Checkuser(user, 1);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
		} finally {

		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/resetPassword", method = RequestMethod.GET)
	public ResponseEntity<?> resetPassword(@RequestParam String newPassword, @RequestParam String userName) {
		logger.info("resetPassword");
		User user = null;
		try {
			user = new User();
			user.setPassword(newPassword);
			user.setUsername(userName);
			user = bo.Checkuser(user, 2);
			return new ResponseEntity<>(HttpStatus.OK); 

		} catch (Exception e) {
			user.setStatus("Network Error Please try again");
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} finally {

		}
	}
	
	// load User data
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public ResponseEntity<?> getUser(@RequestParam String invoice) {
		logger.info("getUser");
		List<UserRole> userlist = null;
		try {
			userlist = new ArrayList<UserRole>();
			userlist = bo.getUser(userlist,invoice);
			logger.info("User List Size --->"+userlist.size());
			return new ResponseEntity<List<UserRole>>(userlist, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("RecentUpdate Exception-->"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
		} finally {
		}
	}

}
