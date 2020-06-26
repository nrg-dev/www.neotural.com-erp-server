package com.mynrg.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
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
import com.mynrg.dao.IssuesInterface;
import com.mynrg.dto.ProductionIssueDataBean;
import com.mynrg.model.IssueComments;
import com.mynrg.model.ProductionIssue;

@RestController
@RequestMapping("/issues")
@SpringBootApplication
public class IssuesService implements Filter{
	
public static final Logger logger = LoggerFactory.getLogger(IssuesService.class);

	
	

@Autowired
NrgBo nrgBo;

	/*
	 * @Autowired(required=true) //@Qualifier("b") //required=true IssuesInterface
	 * issuesinterface;
	 * 
	 * 
	 */

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
	
	ArrayList<String> industryList;
	
	
	// ---------------------------------------- save Issues ----------------------------------------
	
		
		@PostMapping("/save")
		@CrossOrigin(origins = "http://35.160.115.237:80")
		public ResponseEntity<?> save(@RequestBody ProductionIssue issue)throws JSONException
		{
			logger.info("save issues");
			logger.debug("Issue status-->"+issue.getIssueStatus());
			boolean status;//"Fail"; 
			// Gson gson = null;			
			try{
				logger.info("image base 64-->"+issue.getCardImageBase64());
				status = nrgBo.save(issue);
				logger.info("issue reg status-->"+status);
				//gson = new Gson();
			}
			catch(Exception e){
				logger.error("Exception-->",e.getMessage());
				  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
			}
			  return new ResponseEntity<>(HttpStatus.OK);
		}
		
		// ---------------------------------------- save Comments ----------------------------------------
		
		
			@PostMapping("/saveComments")
			@CrossOrigin(origins = "http://35.160.115.237:80")
			public ResponseEntity<?> saveComments(@RequestBody IssueComments comments)throws JSONException
			{
				logger.info("saveComments issues");
				logger.debug("Issue ID-->"+comments.getByissueId());
				logger.debug("Date-->"+comments.getDate());
				logger.info("Comments-->"+comments.getIssueComments());
				boolean status;//"Fail"; 
				try{
					comments.setDate(comments.getDate());
					status = nrgBo.saveComments(comments);
					logger.debug("issue reg status-->"+status);
				}
				catch(Exception e){
					logger.error("Exception-->",e.getMessage());
					  return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
				}
				  return new ResponseEntity<>(HttpStatus.OK);
			}
		// ---------- Get All issue ----------------
	
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@RequestMapping(value="/load",method=RequestMethod.GET)
		public ResponseEntity<?>  load(String status)throws JSONException{
			logger.info("load all issues");
	        List<ProductionIssueDataBean> productionissue=new ArrayList<ProductionIssueDataBean>();
		      try{
		    	//  productionissue = issuesinterface.getProductionIssues();
		    	  productionissue= nrgBo.load(productionissue,status);	
		    	  return new ResponseEntity<List<ProductionIssueDataBean>>(productionissue, HttpStatus.CREATED);

		      }
		      catch(Exception e){
		       e.printStackTrace();
		      }
		    return new ResponseEntity<List<ProductionIssueDataBean>>(productionissue, HttpStatus.CREATED);
		     }
		
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@RequestMapping(value="/loadComments",method=RequestMethod.GET)
		public ResponseEntity<?>  loadComments(int id)throws JSONException{
			logger.info("load loadComments");
	        List<IssueComments> IssueComments=new ArrayList<IssueComments>();
		      try{
		    	//  productionissue = issuesinterface.getProductionIssues();
		    	  IssueComments= nrgBo.loadComments(id);	
		    	  return new ResponseEntity<List<IssueComments>>(IssueComments, HttpStatus.CREATED);

		      }
		      catch(Exception e){
		       e.printStackTrace();
		      }
		    return new ResponseEntity<List<IssueComments>>(IssueComments, HttpStatus.CREATED);
		     }
		
		
		// ---------- Get All issue ----------------
		
			@Produces(MediaType.APPLICATION_JSON)
			@CrossOrigin(origins = "http://35.160.115.237:80")
			@RequestMapping(value="/reportLoad",method=RequestMethod.GET)
			public ResponseEntity<?>  reportLoad()throws JSONException{
				logger.info("load reportLoad");
				Map<String,Integer> map=new HashMap<String,Integer>();
			      try{
			       	  map = nrgBo.reportLoad();	
			    	  return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
			      }
			      catch(Exception e){
			       e.printStackTrace();
			      }
				  return new ResponseEntity<Map<String,Integer>>(map, HttpStatus.OK);
			     }
		
		// ---------- Get single issue ----------------
		
		@Produces(MediaType.APPLICATION_JSON)
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@RequestMapping(value="/get",method=RequestMethod.GET)
		public ProductionIssue  get(@RequestParam int id)throws JSONException{
			System.out.println("inside ProductionIssue view");
			ProductionIssue issue=null;//=new issue();
		      try{
		    	  issue = new ProductionIssue();
		    	  issue= nrgBo.get(id);	
		    	  return issue;
		      }
		      catch(Exception e){
		       e.printStackTrace();
		      }
		    return issue;
		     }
	
	   // ----------- update issue ----------------
		@PutMapping("/update")
		@CrossOrigin(origins = "http://35.160.115.237:80")
		@Produces(MediaType.APPLICATION_JSON)
		public ResponseEntity<ProductionIssue> update(@RequestBody ProductionIssue issue)throws JSONException
		{
			System.out.println("--------Inside Issue  update------------");
			//System.out.println("-------- issue update Id ------------"+issue.getissueId());
			
			logger.info("Inside Issue  update");
			String status="Fail"; 
			try{					
				status = nrgBo.update(issue);
				issue.setStatus("success");
				logger.info("Inside Issue  update  status------------>"+status);				
				return new ResponseEntity<ProductionIssue>(issue, HttpStatus.CREATED);
			}
			catch(Exception e){
				logger.warn("inside exception",e);
				e.printStackTrace();
				return new ResponseEntity<ProductionIssue>(issue, HttpStatus.CREATED);

			}
		}
		
		// ----------  remove issue ----------------
		
		@Produces(MediaType.APPLICATION_JSON)
		@DeleteMapping(value="/remove")
		@CrossOrigin(origins = "http://35.160.115.237:80")
		public void  remove(@RequestParam int id)throws JSONException{
			System.out.println(" Inside Issue   delete");
			String status=null;//=new issue();
		      try{
		    	  status= nrgBo.remove(id);	
		    	  //return status;
		      }
		      catch(Exception e){
			       e.printStackTrace();
		    	 //return status;
		      }
		     }
}
