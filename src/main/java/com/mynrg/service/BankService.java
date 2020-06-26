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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import com.mynrg.bo.NrgBo;
import com.mynrg.model.Bank;

@RestController
@RequestMapping("/bank")
@SpringBootApplication
public class BankService implements Filter{
	
public static final Logger logger = LoggerFactory.getLogger(BankService.class);

	
	

@Autowired
NrgBo nrgBo;

	

	enum Day 
	{ 
	    SUNDAY,MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY,SATURDAY; 
	} 
	
	
	
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
	
	ArrayList<String> industryList;
	
	
	// ---------------------------------------- save  ----------------------------------------
	
		
		@PostMapping("/save")
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		public ResponseEntity<Bank> saveBank(@RequestBody Bank bank)throws JSONException
		{
			logger.info("--------Inside save Bank ------------");
			String status="Fail"; 
			try{
					
				status = nrgBo.saveBank(bank);
				bank.setStatus(status);
				logger.info("saveBank status------------>"+status);
			    return new ResponseEntity<Bank>(bank, HttpStatus.CREATED);
			   // return status;
			}
			catch(Exception e){
				
				logger.warn("inside exception",e);
			    return new ResponseEntity<Bank>(bank, HttpStatus.CREATED);
			   // return status;
			}
		}
		
		// ---------- load ----------------
	
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@RequestMapping(value="/load",method=RequestMethod.GET)
		public ResponseEntity<?>  loadBank()throws JSONException{
	     
			List<Bank> bank=new ArrayList<Bank>();
		      try{
		      
		    	  bank= nrgBo.loadBank(bank);	
		    	  return new ResponseEntity<List<Bank>>(bank, HttpStatus.CREATED);

		      }
		      catch(Exception e){
		       e.printStackTrace();
		      }
		    return new ResponseEntity<List<Bank>>(bank, HttpStatus.CREATED);
		     }
		
		
		// ---------- getBank ----------------
		
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@RequestMapping(value="/get",method=RequestMethod.GET)
		public Bank  getBank(@RequestParam int id)throws JSONException{
			logger.info("inside Bank view");
			Bank bank=null;//=new issue();
		      try{
		    	  bank = new Bank();
		    	  bank= nrgBo.getBank(id);	
		    	  return bank;
		      }
		      catch(Exception e){
		       e.printStackTrace();
		      }
		    return bank;
		     }
	
	   // ----------- update  ----------------
		@PutMapping("/update")
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@Produces(MediaType.APPLICATION_JSON)
		public ResponseEntity<Bank> updateBank(@RequestBody Bank bank)throws JSONException
		{
			logger.info("--------Inside Bank  update------------");		
			logger.info("Inside Bank  update");
			String status="Fail"; 
			try{					
				status = nrgBo.updateBank(bank);
				bank.setStatus("success");
				logger.info("Inside Bank  update  status------------>"+status);				
				return new ResponseEntity<Bank>(bank, HttpStatus.CREATED);
			}
			catch(Exception e){
				logger.warn("inside exception",e);
				e.printStackTrace();
				return new ResponseEntity<Bank>(bank, HttpStatus.CREATED);

			}
		}
		
		// ----------  remove ----------------
		
		@Produces(MediaType.APPLICATION_JSON)
		@DeleteMapping(value="/remove")
		@CrossOrigin(origins = "http://35.160.115.237:80")
		public void  removeBank(@RequestParam int id)throws JSONException{
			logger.info(" Inside removeBank  delete");
			String status=null;//=new issue();
		      try{
		    	  status= nrgBo.removeBank(id);	
		      }
		      catch(Exception e){
			       e.printStackTrace();
		      }
		     }
}
