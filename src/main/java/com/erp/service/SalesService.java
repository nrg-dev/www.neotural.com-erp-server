package com.erp.service;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.erp.bo.ErpBo;
import com.erp.dto.SOInvoiceDto;
import com.erp.dto.Sales;
import com.erp.mongo.dal.RandomNumberDAL;
import com.erp.mongo.dal.SalesDAL;
import com.erp.mongo.dal.StockDAL;
import com.erp.mongo.model.Customer;
import com.erp.mongo.model.Item;
import com.erp.mongo.model.RandomNumber;
import com.erp.mongo.model.SOInvoice;
import com.erp.mongo.model.SOInvoiceDetails;
import com.erp.mongo.model.SOReturnDetails;
import com.erp.mongo.model.SalesOrder;
import com.erp.mongo.model.Stock;
import com.erp.mongo.model.Template;
import com.erp.mongo.model.Transaction;
import com.erp.util.Custom;
import com.erp.util.PDFGenerator;


@RestController
@RequestMapping(value = "/sales")
public class SalesService implements Filter {

	public static final Logger logger = LoggerFactory.getLogger(SalesService.class);

	@Autowired
	ErpBo erpBo;
	
	@Value("${tran.currency}")
	private String currency;
	
	@Value("${stockphase1.status}")
	private String stockstatus1;
	@Value("${stockphase2.status}")
	private String stockstatus2;
	
	@Value("${paymentphase1.status}")
	private String paymentstatus1;
	@Value("${paymentphase2.status}")
	private String paymentstatus2;
	
	@Value("${salesorderphase1.status}")
	private String salesorderstatus1;
	
	@Value("${transinvphase2.status}")
	private String transinvstatus2;
	
	@Value("${transretphase2.status}")
	private String transretstatus2;
	
	@Value("${soinvcash.desc}")
	private String soinvcash;
	
	@Value("${soretcash.desc}")
	private String soretcash;
	
	@Value("${noimage.base64}")
	private String nologo;

	private final SalesDAL salesdal;
	private final RandomNumberDAL randomnumberdal;
	private final StockDAL stockdal;

	public SalesService(SalesDAL salesdal, RandomNumberDAL randomnumberdal, StockDAL stockdal) {
		this.salesdal = salesdal;
		this.randomnumberdal = randomnumberdal;
		this.stockdal = stockdal;
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

	// ------- Load Vendor ----
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/loadCustomer", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadCustomerList() {
		logger.info("loadCustomerList");
		List<Customer> response = new ArrayList<Customer>();
		List<Sales> responseList = new ArrayList<Sales>();
		Sales sales;
		try {
			logger.info("Before Calling loadCustomerList");
			response = salesdal.loadCustomerList(response);
			logger.info("After Calling loadCustomerList");
			for (Customer customerlist : response) {
				sales = new Sales();
				sales.setCustomerName(customerlist.getCustomerName() + "-" + customerlist.getCustcode());
				responseList.add(sales);
			}
			return new ResponseEntity<List<Sales>>(responseList, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {
		}

	}

	// load
	ArrayList<JSONArray> res = new ArrayList<JSONArray>();

	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/load", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadSales() {
		logger.info("------------- Inside loadSales-----------------");
		List<SOInvoice> response = new ArrayList<SOInvoice>();
		List<SOInvoiceDetails> sodetail = new ArrayList<SOInvoiceDetails>();
		List<Sales> responseList = new ArrayList<Sales>();
		Sales sales = null;
		try {
			logger.info("-----------Inside loadSales Called----------");
			response = salesdal.loadSales(response);
			for (SOInvoice res : response) {
				sales = new Sales();
				String itemnameList = "";
				String qtylist = "";
				String totalAmountlist = "";
				String prodList = "";
				sodetail = salesdal.getSales(res.getInvoicenumber());
				for (int i = 0; i < sodetail.size(); i++) {
					logger.info("Product Name -->" + sodetail.get(i).getItemname());
					itemnameList = itemnameList + sodetail.get(i).getItemname() + System.lineSeparator()
							+ System.lineSeparator();
					prodList = prodList + sodetail.get(i).getItemname() + "," + System.lineSeparator();
					logger.info("Qty -->" + sodetail.get(i).getQty());
					qtylist = qtylist + sodetail.get(i).getQty() + System.lineSeparator() + System.lineSeparator();
					logger.info("Total -->" + sodetail.get(i).getSubtotal());
					totalAmountlist = totalAmountlist + sodetail.get(i).getSubtotal() + System.lineSeparator()
							+ System.lineSeparator();
				}
				logger.info("Particular invoice productList -->" + itemnameList);
				sales.setInvoiceNumber(res.getInvoicenumber());
				sales.setSoDate(res.getInvoicedate());
				sales.setCustomerName(res.getCustomername());
				sales.setNetAmount(totalAmountlist);
				sales.setTotalAmount(res.getTotalprice());
				sales.setStatus(res.getStatus());
				sales.setProductName(itemnameList);
				sales.setQuantity(qtylist);
				sales.setUnitPrice(totalAmountlist);
				sales.setDescription(prodList);
				responseList.add(sales);
			}
			return new ResponseEntity<List<Sales>>(responseList, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("loadSales Exception ------------->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {
		}

	}

	// get
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/get", method = RequestMethod.GET)
	public ResponseEntity<?> getSales(String id) {
		logger.info("getSales");
		List<SOInvoiceDetails> responseList = null;
		try {
			logger.debug("Id-->" + id);
			responseList = salesdal.getSales(id);
			return new ResponseEntity<List<SOInvoiceDetails>>(responseList, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/getCustomerDetails", method = RequestMethod.GET)
	public ResponseEntity<?> getCustomerDetails(String customername) {
		logger.info("getCustomerDetails");
		Customer customer = null;
		try {
			logger.debug("Customer Name-->" + customername);
			customer = new Customer();
			String[] res = customername.split("-");
			String customerCode = res[1];
			logger.debug("After Split Customer Name-->" + customerCode);
			customer = salesdal.getCustomerDetails(customerCode);
			customer.setCustomerName(customer.getCustomerName());
			customer.setCity(customer.getCity());
			customer.setCountry(customer.getCountry());
			customer.setPhoneNumber(customer.getPhoneNumber());
			customer.setEmail(customer.getEmail());
			return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// Remove
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/remove", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeSales(String invoiceNumber) {
		logger.info("remove Sales");
		Sales sales = null;
		try {
			sales = new Sales();
			logger.info("Before Calling  removeSales");
			logger.info("Sales code" + invoiceNumber);
			String status = salesdal.removeSales(invoiceNumber);
			sales.setStatus(status);
			logger.info("Successfully Calling removeSales");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			sales.setStatus("failure");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// Remove
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/getUnitPrice", method = RequestMethod.GET)
	public ResponseEntity<?> getUnitPrice(String productName, String category) {
		Item item = null;
		Stock stock = new Stock();
		try {
			item = new Item();
			logger.info("----------- Before Calling  getUnitPrice Sales ----------");
			logger.info("Product Name -->" + productName);
			logger.info("category -->" + category);
			String[] res = productName.split("-");
			String productCode = res[1];
			logger.info("After Split productCode -->" + productCode);
			String[] response = category.split("-");
			String categoryCode = response[1];
			logger.info("After Split categoryCode -->" + categoryCode);
			item = salesdal.getUnitPrice(productCode, categoryCode);
			logger.info("Unit Price ----------" + item.getPrice());			
			stock = stockdal.getAvailableqty(productCode);
			item.setRecentStock(stock.getRecentStock()); 
			logger.debug("Available Qty --->"+stock.getRecentStock());
			return new ResponseEntity<Item>(item, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// ------- Load Item --
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loadItem", method = RequestMethod.GET)
	public ResponseEntity<?> loadItem(String category) {
		logger.info("------------- Inside loadItem -----------------");
		List<Item> item = null;
		Sales sales;
		List<Sales> responseList = new ArrayList<Sales>();
		try {
			logger.info("Category Name -->" + category);
			item = new ArrayList<Item>();
			String[] res = category.split("-");
			String categoryCode = res[1];
			logger.info("After Split Category Code -->" + categoryCode);
			item = salesdal.loadItem(categoryCode);
			for (Item itemList : item) {
				sales = new Sales();
				sales.setProductName(itemList.getProductname() + "-" + itemList.getProdcode());
				responseList.add(sales);
			}
			logger.info("-- list Size --->" + responseList.size());
			return new ResponseEntity<List<Sales>>(responseList, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// Remove
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/removePartId", method = RequestMethod.DELETE)
	public ResponseEntity<?> removePartId(String id, String invoiceNumber) {
		logger.info("removePartId");
		Sales sales = null;
		List<SOInvoiceDetails> responseList = null;
		int temp;
		try {
			sales = new Sales();
			logger.info("Before Calling  remove Particular Sales");
			logger.debug("ObjectID -->" + id);
			logger.debug("salesCode -->" + invoiceNumber);
			responseList = salesdal.getSales(invoiceNumber);
			logger.debug("List Size-->" + responseList.size());
			if (responseList.size() == 0 || responseList.size() == 1) {
				temp = 1;
			} else {
				temp = 2;
			}
			String status = salesdal.removePartId(id, invoiceNumber, temp);
			sales.setStatus(status);
			logger.info("Successfully Called  removeSales");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			sales.setStatus("failure");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// Update
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSales(@RequestBody String saleseditarray) {
		logger.info("updateSales");
		String temp = saleseditarray;
		logger.debug("Edit Sales value-->" + temp);
		Sales sales = null;
		SOInvoice soinvoice = null;
		SOInvoiceDetails sodetails = null;
		int totalQty = 0;
		int totalPrice = 0;
		int totalitem = 0;
		try {
			sales = new Sales();
			logger.info("Get Sales Json -->" + saleseditarray);
			ArrayList<String> list = new ArrayList<String>();
			JSONArray jsonArr = new JSONArray(saleseditarray);
			int remove = 0;
			if (jsonArr != null) {
				for (int i = 0; i < jsonArr.length(); i++) {
					list.add(jsonArr.get(i).toString());
					remove++;
				}
			}
			int postion = remove - 1;
			logger.info("Position ---->" + postion);
			list.remove(postion);
			logger.info("Edit Size -------->" + jsonArr.length());
			// int l = 1;
			for (int i = 0; i < jsonArr.length(); i++) {
				logger.info("Loop 1....");
				JSONArray arr2 = jsonArr.optJSONArray(i);
				if (jsonArr.optJSONArray(i) != null) {
					for (int j = 0; j < arr2.length(); j++) {
						logger.info("Loop 2....");
						if (arr2.getJSONObject(j) != null) {
							JSONObject jObject = arr2.getJSONObject(j);
							logger.info(jObject.getString("productName"));
							logger.info(jObject.getString("category"));
							sodetails = new SOInvoiceDetails();
							sodetails.setCategory(jObject.getString("category"));
							sodetails.setItemname(jObject.getString("productName"));
							sodetails.setDescription(jObject.getString("description"));
							sodetails.setUnitprice(jObject.getString("price"));
							sodetails.setQty(jObject.getString("quantity"));
							sodetails.setSubtotal(jObject.getDouble("netAmount"));
							sodetails.setLastUpdate(Custom.getCurrentInvoiceDate());
							sodetails.setInvoicenumber(jObject.getString("invoiceNumber"));
							sodetails.setSoDate(jObject.getString("soDate"));
							sodetails.setId(jObject.getString("id"));
							salesdal.updateSales(sodetails);
							String str = jObject.getString("quantity");
							str = str.replaceAll("\\D", "");
							totalQty += Integer.valueOf(str);
							totalPrice += jObject.getDouble("netAmount");
							totalitem = j + 1;
						} else {
							logger.info("Null....");
						}
					}
				} else {
					logger.info("Outer Null....");
				}
				// l++;
			}
			soinvoice = new SOInvoice();
			soinvoice = salesdal.loadSOInvoice(sodetails.getInvoicenumber());
			soinvoice.setInvoicenumber(sodetails.getInvoicenumber());
			logger.info("Total Qty -->" + totalQty);
			logger.info("Total Price -->" + totalPrice);
			soinvoice.setQty(totalQty);
			soinvoice.setTotalprice(totalPrice);
			logger.info("After soInvoice Total Qty -->" + soinvoice.getQty());
			logger.info("After soInvoice Total Price -->" + soinvoice.getTotalprice());
			salesdal.updateSOInvoice(soinvoice,1);
			sales.setStatus("success");
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}	

	// Load customer for populate for auto text box
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loadCustomerName", method = RequestMethod.GET)
	public ResponseEntity<?> loadCustomerName() {
		logger.info("loadCustomerName");
		ArrayList<String> customerlist = null;
		try {
			logger.info("Before Calling loadCustomerName");
			customerlist = salesdal.loadCustomerName();
			logger.info("Successfully Calling loadCustomerName");
			return new ResponseEntity<ArrayList<String>>(customerlist, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}

	// ----- Filter Date Data ------
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loadfilterData", method = RequestMethod.POST)
	public ResponseEntity<?> loadfilterData(@RequestBody Sales sales) {
		logger.info("loadfilterData");
		List<SOInvoice> response = new ArrayList<SOInvoice>();
		List<Sales> saleslist = new ArrayList<Sales>();
		List<SOInvoiceDetails> sodetail = new ArrayList<SOInvoiceDetails>();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat your_format = new SimpleDateFormat("dd/MM/yyyy");
		try {
			logger.debug("From Date -->" + sales.getFromdate());
			Date dt1 = format.parse(sales.getFromdate());
			String fromdate = your_format.format(dt1);
			logger.debug("dd/MM/yyyy date-->" + fromdate);
			logger.debug("To Date-->" + sales.getTodate());
			Date dt2 = format.parse(sales.getTodate());
			String todate = your_format.format(dt2);
			logger.debug("dd/MM/yyyy date-->" + todate);
			response = salesdal.loadfilterData(response, fromdate, todate);
			for (SOInvoice res : response) {
				sales = new Sales();
				String itemnameList = "";
				String qtylist = "";
				String totalAmountlist = "";
				String prodList = "";
				sodetail = salesdal.getSales(res.getInvoicenumber());
				for (int i = 0; i < sodetail.size(); i++) {
					logger.debug("Product Name-->" + sodetail.get(i).getItemname());
					itemnameList = itemnameList + sodetail.get(i).getItemname() + System.lineSeparator()
							+ System.lineSeparator();
					prodList = prodList + sodetail.get(i).getItemname() + "," + System.lineSeparator();
					logger.debug("Qty-->" + sodetail.get(i).getQty());
					qtylist = qtylist + sodetail.get(i).getQty() + System.lineSeparator() + System.lineSeparator();
					logger.debug("Total-->" + sodetail.get(i).getSubtotal());
					totalAmountlist = totalAmountlist + sodetail.get(i).getSubtotal() + System.lineSeparator()
							+ System.lineSeparator();
				}
				logger.debug("Particular invoice productList-->" + itemnameList);
				sales.setInvoiceNumber(res.getInvoicenumber());
				sales.setSoDate(res.getInvoicedate());
				sales.setCustomerName(res.getCustomername());
				sales.setNetAmount(totalAmountlist);
				sales.setTotalAmount(res.getTotalprice());
				//sales.setDeliveryCost(res.getDeliveryprice());
				sales.setStatus(res.getStatus());
				sales.setProductName(itemnameList);
				sales.setQuantity(qtylist);
				sales.setUnitPrice(totalAmountlist);
				//sales.setTotalItem(res.getTotalitem());
				sales.setDescription(prodList);
				saleslist.add(sales);
			}
			return new ResponseEntity<List<Sales>>(saleslist, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}
	
	
	// ------- Load Purchase Order --
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/saveSO", method = RequestMethod.POST)
	public ResponseEntity<?> saveSO(@RequestBody SalesOrder salesorder) {
		logger.info("saveSO");
		RandomNumber randomnumber = null;
		int randomId = 7;
		try {
			randomnumber = randomnumberdal.getRandamNumber(randomId);
			String socode = randomnumber.getCode() + randomnumber.getNumber();
			logger.debug("sales code-->" + socode);
			salesorder.setSocode(socode);
			salesorder = salesdal.saveSO(salesorder);
			if (salesorder.getStatus().equalsIgnoreCase("success")) {
				randomnumberdal.updateRandamNumber(randomnumber,randomId);
			}
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
		} finally {

		}
		return new ResponseEntity<>(HttpStatus.OK); // 200
	}
	
	// Update PO
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/updateSalesOrder", method = RequestMethod.PUT)
	public ResponseEntity<?> updateSalesOrder(@RequestBody SalesOrder salesorder) {
		logger.info("---------- updateSalesOrder ----------");
		try {
			boolean status = salesdal.updateSalesOrder(salesorder,2);
			if(status) {
				return new ResponseEntity<>(HttpStatus.OK); 
			}
			else {
				return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);// 500 Internal Server error

			}
		} catch (Exception e) {
			logger.error("Exception-->"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}
	
	// Remove PO Order
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/removeSO", method = RequestMethod.DELETE)
	public ResponseEntity<?> removeSO(String id) {
		logger.info("------- removeSO ----------");
		logger.debug("Service PO delete Id-->"+id);
		try {
			boolean stauts = salesdal.removeSO(id);
			if(stauts) {
				return new ResponseEntity<>(HttpStatus.OK); 
			}
			return new ResponseEntity<>(HttpStatus.OK); 
		} catch (Exception e) {
			logger.error("Exception-->"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}
	
	
	// ------- Load Purchase Order --
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loadSO", method = RequestMethod.GET)
	public ResponseEntity<?> loadSO() {
		logger.info("loadSO");
		List<SalesOrder> solist = null;
		try {
			solist = salesdal.loadSO(1,"all");
			return new ResponseEntity<List<SalesOrder>>(solist, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Exception-->"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}
		
	// Create Invoice
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/createInvoice", method = RequestMethod.POST)
	public ResponseEntity<?> createInvoice(@RequestBody SOInvoiceDto soinvoicedto) {
		logger.info("-------- createInvoice ---------");
		logger.debug("Invoice Date-->" + soinvoicedto.getCreateddate());
		logger.debug("Sub Total-->" + soinvoicedto.getSubtotal());
		logger.debug("Delivery Charge-->" + soinvoicedto.getDeliverycharge());
		logger.debug("Total Price-->" + soinvoicedto.getTotalprice());
		logger.debug("Payment Type-->" + soinvoicedto.getPaymenttype());
		RandomNumber randomnumber = null;
		Sales sales = new Sales();
		int randomId = 11;
		int randomtrId=19;
		Transaction tran = new Transaction();
		List<SalesOrder> solist = new ArrayList<SalesOrder>();
		List<Template> templist = new ArrayList<Template>();
		try {
			templist = salesdal.getTemplateListDetails(templist,"Sales Invoice");
			logger.debug("List Size -->"+templist.size()); 
			if(templist.size() > 0) {
				randomnumber = randomnumberdal.getRandamNumber(randomId);
				String invoice = randomnumber.getCode() + randomnumber.getNumber();
				logger.debug("Invoice number-->" + invoice);
				// Update Invoice Number and get Vendor name and code
				salesdal.updateSO(invoice,soinvoicedto.getOrdernumbers());
				SOInvoice soinvoice = new SOInvoice();
				soinvoice.setInvoicedate(soinvoicedto.getCreateddate());
				logger.debug("Invoice Date-->" + soinvoice.getInvoicedate());
				soinvoice.setInvoicenumber(invoice);
				soinvoice.setPaymenttype(soinvoicedto.getPaymenttype());
				if(soinvoicedto.getPaymenttype().equalsIgnoreCase("cash")) {
					soinvoice.setPaymentstatus(paymentstatus2); 
					soinvoice.setStatus("Success");
				}else {
					soinvoice.setPaymentstatus(paymentstatus1); 
					soinvoice.setStatus("Pending");
				}			
				soinvoice.setSubtotal(soinvoicedto.getSubtotal());
				soinvoice.setDeliveryprice(soinvoicedto.getDeliverycharge());
				soinvoice.setTotalprice(soinvoicedto.getSubtotal()+soinvoicedto.getDeliverycharge());
				for(long v:soinvoicedto.getQty()) {
					soinvoice.setQty(v);
				}
				for(String custcode:soinvoicedto.getCustomercode()) {
					sales.setSoDate(custcode);
				}
				for(String prod:soinvoicedto.getProductname()) {
					soinvoice.setProductname(prod);
				}
				Customer cust = salesdal.getCustomerDetails(sales.getSoDate());
				sales.setCustomerName(cust.getCustomerName());
				sales.setCustomerCity(cust.getCity());
				sales.setCustomerCountry(cust.getCountry());
				sales.setCustomerPhone(cust.getPhoneNumber());
				sales.setCustomerEmail(cust.getEmail());
				soinvoice.setCustomercode(cust.getCustcode());
				soinvoice.setCustomername(cust.getCustomerName()); 
				
				salesdal.saveSOInvoice(soinvoice);
				// Update Random number table
				randomnumberdal.updateRandamNumber(randomnumber,randomId);
				logger.info("createInvoice done!");			
				
				//-- Transaction Table Insert
				if(soinvoicedto.getPaymenttype().equalsIgnoreCase("cash")) {
					logger.info("Payment Type is cash!");
					randomnumber = randomnumberdal.getRandamNumber(randomtrId);
					String traninvoice = randomnumber.getCode() + randomnumber.getNumber();
					logger.debug("Transaction Invoice number-->" + traninvoice);
					tran.setTransactionnumber(traninvoice);
					tran.setTransactiondate(Custom.getCurrentInvoiceDate());
					tran.setDescription(soinvcash);
					tran.setInvoicenumber(invoice);
					tran.setCredit(soinvoicedto.getSubtotal());
					tran.setDebit(0);
					tran.setStatus(transinvstatus2);
					tran.setCurrency(currency);
					salesdal.saveTransaction(tran);
					randomnumberdal.updateRandamNumber(randomnumber,randomtrId);
					logger.info("Transation Insert done!");
				}else {
					logger.info("Payment Type is not cash!");
				}
				
				solist = salesdal.loadSO(2,invoice);
				Template template = salesdal.getTemplateDetails("Sales Invoice");
				String base64=PDFGenerator.getSalesBase64(soinvoice,sales,solist,template);
				logger.info("--------- After Calling Sales PDF Generator -----------");
				soinvoice.setBase64(base64);
				salesdal.updateSOInvoice(soinvoice,3);
				
				return new ResponseEntity<>(HttpStatus.OK);
			}else {
				logger.info("--------- Template Details Null -----------");
				return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED); // 417
			}
			
		}catch(Exception e) {
			logger.error("Exception-->"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 400

		}
	}	
	
	//--------- Load SOInvoice List -----------
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/loadInvoice", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadInvoice() {
		logger.info("----- loadInvoice -------");
		List<SOInvoice> responselist = new ArrayList<SOInvoice>();
		String paystatus = "All";
		try {
			responselist = salesdal.loadInvoice(paystatus);
			return new ResponseEntity<List<SOInvoice>>(responselist, HttpStatus.OK);				
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); 
		} finally {
		}
	}

	// Create Return
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/createReturn", method = RequestMethod.POST)
	public ResponseEntity<?> createReturn(@RequestBody SOReturnDetails soreturn) {
		logger.info("--------- createReturn -------");
		logger.debug("SO CUstomer Name -->" + soreturn.getCustomername());
		logger.debug("SO Item Name -->" + soreturn.getItemname());
		logger.debug("SO Invoiced Qty-->" + soreturn.getInvoicedqty());
		logger.debug("SO Date -->" + soreturn.getInvoiceddate());
		logger.debug("SO Item Status -->" + soreturn.getItemStatus());
		logger.debug("SO Payment Status -->" + soreturn.getReturnStatus());
		logger.debug("SO Qty -->" + soreturn.getQty());
		logger.debug("SO Price -->" + soreturn.getPrice());
		logger.debug("SoCode-->" + soreturn.getSocode());
		RandomNumber randomnumber = null;
		int randomId=9;
		int randomtrId=19;
		Transaction trans = new Transaction();
		SalesOrder salesorder = new SalesOrder();
		try {
			randomnumber = randomnumberdal.getRandamNumber(randomId);
			String invoice = randomnumber.getCode() + randomnumber.getNumber();
			logger.debug("Invoice number -->" + invoice);
			//purchasedal.updateSO(invoice,poinvoicedto.getOrdernumbers());
			soreturn.setInvoicenumber(invoice);
			soreturn.setCreateddate(Custom.getCurrentInvoiceDate());
			if(soreturn.getReturnStatus().equalsIgnoreCase("cash")) {
				soreturn.setPaymentstatus(paymentstatus2); 
			}else {
				soreturn.setPaymentstatus(paymentstatus1); 
			}
			soreturn.setStatus("Active"); 
			salesdal.insertReturn(soreturn);
			randomnumberdal.updateRandamNumber(randomnumber,randomId);

			salesorder.setInvoicenumber(soreturn.getSocode()); 
			salesdal.updateSalesOrder(salesorder,1);
			logger.info("create SalesReturn done!");
			
			//-- Transaction Table Insert
			if(soreturn.getReturnStatus().equalsIgnoreCase("cash")) {
				logger.info("Payment Type is cash!");
				randomnumber = randomnumberdal.getRandamNumber(randomtrId);
				String traninvoice = randomnumber.getCode() + randomnumber.getNumber();
				logger.debug("Return Transaction Invoice number-->" + traninvoice);
				trans.setTransactionnumber(traninvoice);
				trans.setTransactiondate(Custom.getCurrentInvoiceDate());
				trans.setDescription(soretcash);
				trans.setInvoicenumber(invoice);
				long totalAmount = soreturn.getPrice() * Integer.valueOf(soreturn.getQty());
				trans.setCredit(totalAmount);
				trans.setDebit(0);
				trans.setStatus(transretstatus2);
				trans.setCurrency(currency);
				salesdal.saveTransaction(trans);
				randomnumberdal.updateRandamNumber(randomnumber,randomtrId);
				logger.info("Return Transation Insert done!");
			}else {
				logger.info("Payment Type is credit and voucher!");
			}	
			return new ResponseEntity<>(HttpStatus.OK); // 200
		}catch(Exception e) {
			logger.error("Exception-->"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 400
		}
	}
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/loadReturn", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadReturn() {
		logger.info("-------- loadReturn ----------");
		List<SOReturnDetails> responselist = new ArrayList<SOReturnDetails>();
		String paystatus = "All";
		try {
			responselist = salesdal.loadReturn(paystatus);
			return new ResponseEntity<List<SOReturnDetails>>(responselist, HttpStatus.OK);				
		} catch (Exception e) {
			logger.error("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST); // 400
		} finally {
		}
	}
	
	// get Company Details
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/getTemplateDetails", method = RequestMethod.GET)
	public ResponseEntity<?> getTemplateDetails(String templateType) {
		logger.info("getTemplateDetails");
		Template template = null;
		try {
			template = salesdal.getTemplateDetails(templateType);
			return new ResponseEntity<Template>(template, HttpStatus.CREATED);
		} catch (Exception e) {
			logger.info("Exception-->" + e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

		} finally {

		}
	}
	
	//Save/Update Details
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/addTemplateDetails", method = RequestMethod.POST)
	public ResponseEntity<?> addTemplateDetails(@RequestBody Template template) {
		logger.info("Sales AddTemplateDetails");
		logger.debug("Company Name-->" + template.getCompanyname());
		logger.debug("Address-->" + template.getAddress());
		logger.debug("City Name-->" + template.getCity());
		logger.debug("Country Name-->" + template.getCountry());
		logger.debug("Type-->" + template.getTemplateType());
		try {
			if(template.getCompanylogo()!= null) {
				logger.info("Sales Company Image not null"); 
			}else {
				logger.info("Sales Company Image Null");
				template.setCompanylogo(nologo);
			}
			salesdal.addTemplateDetails(template);
			return new ResponseEntity<>(HttpStatus.OK); // 200
		}catch(Exception e) {
			logger.error("Exception-->"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 400
		}
	}
	
	// ------- Load Purchase Order --
	@CrossOrigin(origins = "http://localhost:8080")
	@RequestMapping(value = "/loadDashSO", method = RequestMethod.GET)
	public ResponseEntity<?> loadDashSO() {
		logger.info("loadDashSO");
		List<SalesOrder> solist = null;
		try {
			solist = salesdal.loadSO(3,"Open");
			return new ResponseEntity<List<SalesOrder>>(solist, HttpStatus.CREATED);

		} catch (Exception e) {
			logger.error("Exception-->"+e.getMessage());
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} finally {

		}
	}
	
}
