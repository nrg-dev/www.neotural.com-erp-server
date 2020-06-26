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
import com.mynrg.model.Connection;

@RestController
@RequestMapping("/connection")
@SpringBootApplication
public class ConnectionService implements Filter{
	
public static final Logger logger = LoggerFactory.getLogger(ConnectionService.class);

	
	

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
	
	
	// ---------------------------------------- save connection ----------------------------------------
	
		
		@PostMapping("/save")
		@CrossOrigin(origins = "http://35.160.115.237:80")
		public String saveConnection(@RequestBody Connection connection)throws JSONException
		{
			logger.info("--------Inside saveConnection ------------");
			logger.info("issuereg");
			String status="Fail"; 
			 Gson gson = null;			
			try{
					
				status = nrgBo.saveConnection(connection);
				logger.info("saveConnection status------------>"+status);
				gson = new Gson();
			}
			catch(Exception e){
				logger.warn("inside exception",e);
				e.printStackTrace();
			}
			return gson.toJson(status);
		}
		
		// ---------- loadConnection ----------------
	
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@RequestMapping(value="/load",method=RequestMethod.GET)
		public ResponseEntity<?>  loadConnection()throws JSONException{
	     
			List<Connection> connection=new ArrayList<Connection>();
		      try{
		      
		    	  connection= nrgBo.loadConnection(connection);	
		    	 
				  return new ResponseEntity<List<Connection>>(connection, HttpStatus.CREATED);

		      }
		      catch(Exception e){
		       e.printStackTrace();
		      }
		    return new ResponseEntity<List<Connection>>(connection, HttpStatus.CREATED);
		     }
		
		
		// ---------- getConnection ----------------
		
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@RequestMapping(value="/get",method=RequestMethod.GET)
		public Connection  getConnection(@RequestParam int id)throws JSONException{
			logger.info("inside Connection view");
			Connection connection=null;//=new issue();
		      try{
		    	  connection = new Connection();
		    	  connection= nrgBo.getConnection(id);	
		    	  return connection;
		      }
		      catch(Exception e){
		       e.printStackTrace();
		      }
		    return connection;
		     }
	
	   // ----------- updateConnection  ----------------
		@PutMapping("/update")
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@Produces(MediaType.APPLICATION_JSON)
		public ResponseEntity<Connection> updateConnection(@RequestBody Connection connection)throws JSONException
		{
			logger.info("--------Inside Connection  update------------");		
			logger.info("Inside Issue  update");
			String status="Fail"; 
			try{					
				status = nrgBo.updateConnection(connection);
				connection.setStatus("success");
				logger.info("Inside Connection  update  status------------>"+status);				
				return new ResponseEntity<Connection>(connection, HttpStatus.CREATED);
			}
			catch(Exception e){
				logger.warn("inside exception",e);
				e.printStackTrace();
				return new ResponseEntity<Connection>(connection, HttpStatus.CREATED);

			}
		}
		
		// ----------  removeConnection ----------------
		
		@Produces(MediaType.APPLICATION_JSON)
		@DeleteMapping(value="/remove")
		@CrossOrigin(origins = "http://35.160.115.237:80")
		public void  removeConnection(@RequestParam int id)throws JSONException{
			logger.info(" Inside removeConnection   delete");
			String status=null;//=new issue();
		      try{
		    	  status= nrgBo.removeConnection(id);	
		      }
		      catch(Exception e){
			       e.printStackTrace();
		      }
		     }
}
