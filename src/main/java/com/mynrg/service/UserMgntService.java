package com.mynrg.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;*/

//import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.codehaus.jettison.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/*import java.io.IOException;
*/import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
/*import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Collection;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;*/
//import org.springframework.http.MediaType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import com.mynrg.bo.NrgBo;
import com.mynrg.bo.UserBo;
import com.mynrg.dto.LoginDataBean;
import com.mynrg.model.Portal;
import com.mynrg.model.ProductionIssue;
import com.mynrg.model.User;

@RestController
@RequestMapping("/usermgnt")
@SpringBootApplication
public class UserMgntService implements Filter{
	
public static final Logger logger = LoggerFactory.getLogger(UserMgntService.class);

	
	

@Autowired
UserBo userbo;

	/*enum Industry 
    { 
		CivilEngineering, SoftwareDevelopment, Others; 
    } */

	enum Day 
	{ 
	    SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY; 
	} 
	
	/*enum Month 
	{ 
		January,February,March,April,May,June,July,August,September,October,November,December; 
	} */
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {

	    HttpServletRequest request = (HttpServletRequest) req;
	    HttpServletResponse response = (HttpServletResponse) res;
	    response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
	    response.setHeader("Access-Control-Allow-Credentials", "true");
	    response.setHeader("Access-Control-Allow-Methods", "POST, PUT,GET, OPTIONS, DELETE");
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
	
		// Validate Login
	
	// ---------- Get All  User list ----------------
	
			@Produces(MediaType.APPLICATION_JSON)
			@CrossOrigin(origins = "http://35.160.115.237:80")
			@RequestMapping(value="/isAuthentication",method=RequestMethod.GET)
			public ResponseEntity<?>  isAuthentication(String username,String password) {
				logger.debug("UserName-->"+username);
				logger.debug("Password-->"+password);

				LoginDataBean resp =new LoginDataBean();
				User response=null;//new ArrayList<User>();
			      try{
			    	  response= userbo.isAuthentication(username,password);	
			    	  if(response!=null && password.equalsIgnoreCase(response.getUserPassword())) {
			    		  logger.debug("Data found");
			    		  resp.setUsertype(response.getUserType());
			    		  resp.setUserstatus("success");
			    	  } else {
			    		  resp.setUserstatus("failure");
			    		  logger.debug("No Data found");
			    	  }
			    	  return new ResponseEntity<LoginDataBean>(resp, HttpStatus.CREATED);
			      }
			      catch(Exception e){
			       e.printStackTrace();
			      }
			    return new ResponseEntity<LoginDataBean>(resp, HttpStatus.CREATED);
			     }
			
			
		
		// ---------- Get All  User list ----------------
	
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@RequestMapping(value="/loadUserInfo",method=RequestMethod.GET)
		public ResponseEntity<?>  loadUserInfo(String type) {
	     logger.debug("Type-->"+type);
			List<LoginDataBean> list=new ArrayList<LoginDataBean>();
			List<User> response=null;//new ArrayList<User>();
		      try{
		    	  response= userbo.loadUserInfo(type);	
		    	  for(User res : response) {
		    		  LoginDataBean bean = new LoginDataBean();
		    		  bean.setUsername(res.getUserName());
		    		  bean.setUserpassword(res.getUserPassword());
		    		  bean.setUsertype(res.getUserType());
		    		  bean.setUserstatus(res.getUserStatus());
		    		  bean.setEmailId(res.getUserEmailid());
		    		  bean.setId(res.getUserId());
		    		  list.add(bean);
		    	  }
		 	      logger.debug("List Size-->"+list.size());
				  return new ResponseEntity<List<LoginDataBean>>(list, HttpStatus.CREATED);
		      }
		      catch(Exception e){
		       e.printStackTrace();
		      }
		    return new ResponseEntity<List<LoginDataBean>>(list, HttpStatus.CREATED);
		     }
		
		
}
