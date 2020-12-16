package com.erp.mongo.dal;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.erp.mongo.model.Index;
import com.erp.mongo.model.Item;
import com.erp.mongo.model.POInvoice;
import com.erp.mongo.model.POInvoiceDetails;
import com.erp.mongo.model.POReturnDetails;
import com.erp.mongo.model.PurchaseOrder;
import com.erp.mongo.model.RecentUpdates;
import com.erp.mongo.model.Template;
import com.erp.mongo.model.Transaction;
import com.erp.mongo.model.Vendor;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;

@Repository
public class PurchaseImpl implements PurchaseDAL {

	public static final Logger logger = LoggerFactory.getLogger(PurchaseImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private SequenceDAL sequencedal;
	

	@Value("${stockphase1.status}")
	private String stockstatus1;
	@Value("${stockphase2.status}")
	private String stockstatus2;
	
	@Value("${paymentphase1.status}")
	private String paymentstatus1;
	@Value("${paymentphase2.status}")
	private String paymentstatus2;
	
	@Value("${invoicephase1.status}")
	private String invoicestatus1;
	@Value("${invoicephase2.status}")
	private String invoicestatus2;
	
	@Value("${purchaseorderphase2.status}")
	private String purchaseorderstatus2;
	
	@Value("${purchaseorderphase3.status}")
	private String purchaseorderstatus3;
	
	@Value("${pophase2.status}")
	private String pophase2status;
	
	@Value("${pophase3.status}")
	private String pophasestatus3;
	
	@Value("${pophase4.status}")
	private String pophasestatus4;
	
	/*
	 * @Autowired ErpBo investmentBo1;
	 */

	// Save PO Invoice
	public POInvoice savePOInvoice(POInvoice poinvoice) {
		logger.info("savePOInvoice");
		logger.info("Before save Invoice");
		Index index = new Index();
		index.setKey(poinvoice.getInvoicenumber());
		index.setValue(poinvoice.getInvoicenumber()+"-"+poinvoice.getStatus()+" -purcahseinvoice");
		mongoTemplate.save(index);
		mongoTemplate.save(poinvoice);
		logger.info("After save Invoice");
		
		//---- Insert into RecentUpdate Table
		RecentUpdates recent = new RecentUpdates();
		recent.setDate(poinvoice.getInvoicedate());
		recent.setId(sequencedal.generateSequence("recent")); 
		recent.setInvoicenumber(poinvoice.getInvoicenumber());
		recent.setProductname(poinvoice.getProductname());
		recent.setQty(poinvoice.getQty());
		recent.setStatus("invoice");
		recent.setUnitprice(poinvoice.getSubtotal());
		recent.setVendorcode(poinvoice.getVendorcode());
		recent.setVendorname(poinvoice.getVendorname()); 
		mongoTemplate.save(recent);
		
		return poinvoice;
	}

//	// Save PO Invoice details
//	@Override
//	public POInvoiceDetails savePurchase(POInvoiceDetails purchaseorder) {
//		logger.info("Before save PO Invoice details");
//		Index index = new Index();
//		index.setKey(purchaseorder.getInvoicenumber());
//		index.setValue(purchaseorder.getInvoicenumber()+"-"+purchaseorder.getStatus()+" -purcahseorder");
//		mongoTemplate.save(index);
//		mongoTemplate.save(purchaseorder);
//		logger.info("After save Invoice details");
//		return purchaseorder;
//	}

	public List<Vendor> loadVendorList(List<Vendor> list) {
		list = mongoTemplate.findAll(Vendor.class);// .find(query, OwnTree.class); return
		return list;

	}

	/*
	 * public List<POInvoice> loadPurchase(List<POInvoice> list) { //
	 * List<PurchaseOrder> list = mongoTemplate.findAll(POInvoice.class);//
	 * .find(query, OwnTree.class); return return list; }
	 */
	
	public List<POInvoice> loadInvoice(String paystatus,String invoicenumber){
		// List<PurchaseOrder>loadInvoice
		List<POInvoice> list = new ArrayList<POInvoice>();
		if(paystatus.equalsIgnoreCase("All") && invoicenumber.equalsIgnoreCase("All")) {
			Query query = new Query();
		    query.with(new Sort(new Order(Direction.DESC, "invoicenumber")));
			list = mongoTemplate.find(query,POInvoice.class);
		}else if(paystatus.equalsIgnoreCase("Pending")) {
			Query query = new Query();
		    query.with(new Sort(new Order(Direction.DESC, "invoicenumber")));
		    query.addCriteria(Criteria.where("paymentstatus").is(paystatus));
			list = mongoTemplate.find(query,POInvoice.class);
		}else if(paystatus.equalsIgnoreCase("All") && invoicenumber != null) {
			Query query = new Query();
		    query.with(new Sort(new Order(Direction.DESC, "invoicenumber")));
		    query.addCriteria(Criteria.where("invoicenumber").is(invoicenumber));
			list = mongoTemplate.find(query,POInvoice.class);
		}
		logger.debug("Size-->"+list.size());
		for (POInvoice e : list) {
		    logger.debug("Invoice Number -->"+e.getInvoicenumber());    
		}
		
		//List<POInvoice> list = mongoTemplate.findAll(POInvoice.class);// Load Invoice
		return list;
	
	}


	// get Purchase on Impl
	@Override
	public List<POInvoiceDetails> getPurchase(String invoiceNumber) {
		List<POInvoiceDetails> podetaillist;
		Query query = new Query();
		query.addCriteria(Criteria.where("invoicenumber").is(invoiceNumber));
		podetaillist = mongoTemplate.find(query, POInvoiceDetails.class);
		return podetaillist;
	}

	@Override
	public Vendor getVendorDetails(String vendorCode) {
		Vendor vendor;
		Query query = new Query();
		query.addCriteria(Criteria.where("vendorcode").is(vendorCode));
		vendor = mongoTemplate.findOne(query, Vendor.class);
		return vendor;
	}

	// remove
	@Override
	public String removePurchase(String invoiceNumber) {
		String response = "failure";
		Query query = new Query();
		query.addCriteria(Criteria.where("invoicenumber").is(invoiceNumber));
		mongoTemplate.remove(query, POInvoiceDetails.class);
		mongoTemplate.remove(query, POInvoice.class);
		response = "Success";
		return response;
	}

	// remove
	@Override
	public String removePartId(String id, String invoiceNumber, int temp) {
		String response = "failure";
		Query query = new Query();
		Query query2 = new Query();
		query.addCriteria(new Criteria().andOperator(Criteria.where("id").is(id),
				Criteria.where("invoicenumber").is(invoiceNumber)));
		if (temp == 1) {
			mongoTemplate.remove(query, POInvoiceDetails.class);
			query2.addCriteria(Criteria.where("invoicenumber").is(invoiceNumber));
			mongoTemplate.remove(query2, POInvoice.class);
		} else if (temp == 2) {
			mongoTemplate.remove(query, POInvoiceDetails.class);
		}
		response = "Success";
		return response;
	}

	@Override
	public List<Item> loadItem(String categoryCode) {
		List<Item> list;
		Query query = new Query();
		query.addCriteria(Criteria.where("categorycode").is(categoryCode));
		list = mongoTemplate.find(query, Item.class);
		return list;
	}

	@Override
	public Item getUnitPrice(String productCode, String categoryCode) {
		Item item;
		Query query = new Query();
		query.addCriteria(Criteria.where("prodcode").is(productCode));
		/*
		 * query.addCriteria( new Criteria().andOperator(
		 * Criteria.where("categorycode").is(categoryCode),
		 * Criteria.where("prodcode").is(productCode) ) );
		 */
		item = mongoTemplate.findOne(query, Item.class);
		return item;
	}

	// update PoDetails
	@Override
	public POInvoiceDetails updatePurchase(POInvoiceDetails purchase) {
		Update update = new Update();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(purchase.getId()));
		
		update.set("invoicenumber", purchase.getInvoicenumber());
		update.set("category", purchase.getCategory());
		update.set("itemname", purchase.getItemname());
		update.set("qty", purchase.getQty());
		update.set("description", purchase.getDescription());
		update.set("unitprice", purchase.getUnitprice());
		update.set("subtotal", purchase.getSubtotal());
		update.set("poDate", purchase.getPoDate());
		update.set("lastUpdate", purchase.getLastUpdate());
		update.set("paymentStatus", purchase.getPaymentStatus());
		update.set("remainingAmount", purchase.getRemainingQty());		
		mongoTemplate.updateFirst(query, update, POInvoiceDetails.class);

		return purchase;
	}
	
	@Override
	public POInvoice loadPOInvoice(String invoicenumber) {
		POInvoice poinvoice;
		Query query = new Query();
		query.addCriteria(Criteria.where("invoicenumber").is(invoicenumber));
		poinvoice = mongoTemplate.findOne(query, POInvoice.class);
		return poinvoice;
	}
	
	@Override 
	public POInvoice updatePOInvoice(POInvoice purchase, int i) {
		logger.info("Update POInvoice Number --->"+purchase.getInvoicenumber());
		Update update = new Update();
		Query query = new Query();
		query.addCriteria(Criteria.where("invoicenumber").is(purchase.getInvoicenumber()));
		if(i == 1) {
			update.set("invoicedate", purchase.getInvoicedate());
			update.set("invoicenumber", purchase.getInvoicenumber());
			update.set("vendorname", purchase.getVendorname());
			update.set("vendorcode", purchase.getVendorcode());
			update.set("qty", purchase.getQty());
			update.set("subtotal", purchase.getSubtotal());
			update.set("deliveryprice",purchase.getDeliveryprice());
			update.set("totalprice", purchase.getTotalprice());
			update.set("status", pophasestatus3);
			update.set("stockstatus", stockstatus2);
			update.set("base64", purchase.getBase64());
			update.set("pophasestatus", pophasestatus3);
			//mongoTemplate.updateFirst(query,update, POInvoice.class);
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), POInvoice.class);
		}else if(i == 2) {
			logger.debug("Transaction Based POInvoice Payment Status Update -->");
			update.set("paymentstatus", paymentstatus2);
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), POInvoice.class);
			logger.debug("After POInvoice Payment Status Update -->");
		}else if(i == 3) {
			update.set("base64", purchase.getBase64());
			//update.set("status", invoicestatus2);
			//update.set("stockstatus", stockstatus2);
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), POInvoice.class);
		}else if(i == 4) {
			update.set("status", pophase2status);
			update.set("base64", purchase.getBase64());
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), POInvoice.class);
			logger.debug("After POInvoice Received Status Update -->");
		}else if(i == 5) {
			update.set("pophasestatus", "Returned");
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), POInvoice.class);
			logger.debug("After POInvoice Returned Status Update -->");
		}else if(i == 6) {
			update.set("status", pophasestatus4);
			update.set("base64", purchase.getBase64());
			update.set("pophasestatus", pophasestatus4);
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), POInvoice.class);
			logger.debug("After POInvoice Returned Status Update -->");
		}
		return purchase; 
	}
	
	/*
	 * // update POInvoice
	 * 
	 * @Override public POInvoice updatePOInvoice(POInvoice purchase) { Update
	 * update = new Update(); Query query = new Query();
	 * query.addCriteria(Criteria.where("invoicenumber").is(purchase.
	 * getInvoicenumber())); update.set("invoicedate", purchase.getInvoicedate());
	 * update.set("invoicenumber", purchase.getInvoicenumber());
	 * update.set("vendorname", purchase.getVendorname());
	 * update.set("deliveryprice", purchase.getDeliveryprice());
	 * update.set("totalqty", purchase.getTotalqty()); update.set("totalprice",
	 * purchase.getTotalprice()); update.set("totalitem", purchase.getTotalitem());
	 * update.set("status", purchase.getStatus()); mongoTemplate.updateFirst(query,
	 * update, POInvoice.class); return purchase; }
	 */
	// Save PO Return details
	@Override
	public POReturnDetails insertReturn(POReturnDetails purchasereturn) {
		logger.info("Before save PO Return details");
		mongoTemplate.save(purchasereturn);
		logger.info("After save PO Return details");
		return purchasereturn;
	}
	
	// Vendor item load
	public List<Item> loadVendorItem(List<Item> itemlist, String vendorCode) {
		logger.info("DAO VendorCode -->" + vendorCode);
		if (vendorCode.equalsIgnoreCase("") || vendorCode.equalsIgnoreCase(null)) {
			logger.info("DAO Vendor item load all");
			itemlist = mongoTemplate.findAll(Item.class);
			logger.debug("DAO item size -->" + itemlist.size());

		} else {
			Query query = new Query();
			query.addCriteria(Criteria.where("vendorcode").is(vendorCode));
			itemlist = mongoTemplate.find(query, Item.class);

		}

		return itemlist;
	}
	
	//----- Load PurchaseInvoice Based on date --
	public List<POInvoice> loadfilterData(List<POInvoice> list,String fromdate, String todate) {
		list = mongoTemplate.find(
                Query.query(Criteria.where("invoicedate").gte(fromdate).lt(todate)),POInvoice.class);
		return list;
	}
	
	public List<PurchaseOrder> loadPO(int temp,String invoice){
		List<PurchaseOrder> list=null;
		logger.debug("POCode --->"+invoice);
		Query query = new Query();
		if(temp == 1) {
			query.with(new Sort(new Order(Direction.DESC, "pocode")));
			query.addCriteria(Criteria.where("status").is("Open"));
			list = mongoTemplate.find(query,PurchaseOrder.class);
		}else if(temp == 2) {
			query.addCriteria(Criteria.where("invoicenumber").is(invoice));
			list = mongoTemplate.find(query,PurchaseOrder.class);
		}else if(temp == 3) {
			query.addCriteria(Criteria.where("invoicenumber").is(invoice));
			query.addCriteria( new Criteria().orOperator(
					Criteria.where("postatus").is(""),Criteria.where("postatus").is(null) ));
			list = mongoTemplate.find(query,PurchaseOrder.class);
		}else if(temp == 4) {
			query.addCriteria(Criteria.where("pocode").is(invoice));
			query.addCriteria(Criteria.where("postatus").is("Returned"));
			list = mongoTemplate.find(query,PurchaseOrder.class);
		}
		
		logger.debug("Size-->"+list.size());
		return list;
	}
	
	public PurchaseOrder savePO(PurchaseOrder purchaseorder) {
		logger.info("DAO PurchaseOrder");
		logger.debug("PO Number-->"+purchaseorder.getPocode());
		Index index = new Index();
		index.setKey(purchaseorder.getInvoicenumber());
		index.setValue(purchaseorder.getInvoicenumber()+"-"+purchaseorder.getStatus()+" -purchaseorder");
		mongoTemplate.save(index);
		purchaseorder.setId(sequencedal.generateSequence("order"));
		mongoTemplate.save(purchaseorder);
		purchaseorder.setStatus("success"); 
		
		//---- Insert into RecentUpdate Table
		RecentUpdates recent = new RecentUpdates();
		recent.setDate(purchaseorder.getDate());
		recent.setId(sequencedal.generateSequence("recent")); 
		recent.setInvoicenumber(purchaseorder.getPocode());
		recent.setProductcode(purchaseorder.getProductcode());
		recent.setProductname(purchaseorder.getProductname());
		recent.setQty(purchaseorder.getQty());
		recent.setStatus("Order");
		recent.setUnitprice(purchaseorder.getUnitprice());
		recent.setVendorcode(purchaseorder.getVendorcode());
		recent.setVendorname(purchaseorder.getVendorname()); 
		mongoTemplate.save(recent);
		
		return purchaseorder;
	}
	
	// Update PO With Invoice Number
	public boolean updatePO(String invoice,String[] value) {
		logger.info("DAO updatePO");
		Update update = null;//new Update();
		Query query = null;//new Query();
		try {
			for(String v:value) {
				update = new Update();
				query = new Query();
				logger.debug("PO numbers-->"+v);
				query.addCriteria(Criteria.where("pocode").is(v));
				update.set("invoicenumber", invoice);
				update.set("status", purchaseorderstatus2);
				mongoTemplate.updateFirst(query, update, PurchaseOrder.class);
			}
			logger.info("updatePO done!");
			return true;
		}catch(Exception e) {
			logger.error("Exception-->"+e.getMessage());
			return false;
		}finally {
			update=null;
			query=null;
		}
		
	}
	
	// Update PO order
	public boolean updatePurchaseOrder(PurchaseOrder purchaseorder,int i) {
		Update update = new Update();
		Query query = new Query();
		Query query1 = new Query();
		if(i == 1) {
			logger.debug("Return Invoice Number -->"+purchaseorder.getInvoicenumber()+"  Return Status -->"+purchaseorderstatus3); 
			query.addCriteria(Criteria.where("pocode").is(purchaseorder.getInvoicenumber()));
			update.set("status", purchaseorderstatus3);
			update.set("returnqty", purchaseorder.getReturnqty());
			update.set("postatus", "Returned");
			logger.info("After POReturn Status Update -->");
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), PurchaseOrder.class);
		}else if(i == 2) {
			query.addCriteria(Criteria.where("_id").is(purchaseorder.getId()));
			update.set("categoryname", purchaseorder.getCategoryname());
			update.set("categorycode", purchaseorder.getCategorycode());
			update.set("productname", purchaseorder.getProductname());
			update.set("productcode", purchaseorder.getProductcode());
			update.set("vendorname", purchaseorder.getVendorname());
			update.set("vendorcode", purchaseorder.getVendorcode());
			update.set("qty", purchaseorder.getQty());
			update.set("unit", purchaseorder.getUnit());
			update.set("unitprice", purchaseorder.getUnitprice());
			update.set("subtotal", purchaseorder.getSubtotal());
			update.set("date", purchaseorder.getDate());
			update.set("description", purchaseorder.getDescription());
			mongoTemplate.updateFirst(query, update, PurchaseOrder.class);
			
			//---- Update into RecentUpdate Table
			logger.info("Recent InvoiceNumber -->"+purchaseorder.getPocode());
			query1.addCriteria(Criteria.where("invoicenumber").is(purchaseorder.getPocode()));
			update.set("productname", purchaseorder.getProductname());
			update.set("productcode", purchaseorder.getProductcode());
			update.set("vendorname", purchaseorder.getVendorname());
			update.set("vendorcode", purchaseorder.getVendorcode());
			update.set("qty", purchaseorder.getQty());
			update.set("unitprice", purchaseorder.getUnitprice());
			mongoTemplate.findAndModify(query1, update,
					new FindAndModifyOptions().returnNew(true), RecentUpdates.class);
			
		}if(i == 3) {
			logger.debug("Return Invoice Number -->"+purchaseorder.getInvoicenumber()+"  Partial Status -->"+pophasestatus4); 
			query.addCriteria(Criteria.where("pocode").is(purchaseorder.getInvoicenumber()));
			update.set("postatus", pophasestatus4);
			logger.info("After Partial Status Update -->");
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), PurchaseOrder.class);
		}else if(i == 4) {
			logger.debug("Return Invoice Number -->"+purchaseorder.getPocode()); 
			query.addCriteria(Criteria.where("pocode").is(purchaseorder.getPocode()));
			update.set("status", purchaseorderstatus2);
			update.set("postatus", "");
			logger.info("After POReturn Status Update -->");
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), PurchaseOrder.class);
		}
		return true;
	}
		

	// Remove
	public boolean removePO(String id) {
		logger.info("PO delete Id-->"+id);
		logger.info("PO delete start");
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		mongoTemplate.remove(query, PurchaseOrder.class);
		logger.debug("PO deleted"+id);
		return true;
	}
	
	@Override
	public List<POReturnDetails> loadReturn(String paystatus,String pocode) {
		List<POReturnDetails> list = new ArrayList<POReturnDetails>();
		if(paystatus.equalsIgnoreCase("All") && pocode.equalsIgnoreCase("All")) {
			Query query = new Query();
		    query.with(new Sort(new Order(Direction.DESC, "invoicenumber")));
			query.addCriteria(Criteria.where("status").is("Active"));
			list = mongoTemplate.find(query,POReturnDetails.class);
		}else if(paystatus.equalsIgnoreCase("Pending")) {
			Query query = new Query();
		    query.with(new Sort(new Order(Direction.DESC, "invoicenumber")));
			query.addCriteria(Criteria.where("status").is("Active"));
		    query.addCriteria(Criteria.where("paymentstatus").is(paystatus));
			list = mongoTemplate.find(query,POReturnDetails.class);
		}else if(paystatus.equalsIgnoreCase("All") && pocode != null) {
			Query query = new Query();
		    query.with(new Sort(new Order(Direction.DESC, "invoicenumber")));
			query.addCriteria(Criteria.where("pocode").is(pocode));
			query.addCriteria(Criteria.where("status").is("Active"));
			list = mongoTemplate.find(query,POReturnDetails.class);
		}		
		logger.debug("Return List Size-->"+list.size());
		return list;

	}
	
	//--- Insert Transaction Table ---
	public Transaction saveTransaction(Transaction trans) {
		logger.info("DAO saveTransaction");
		mongoTemplate.save(trans);
		trans.setStatus("success"); 
		return trans;
	}
	
	//-------- Update PoReturn Table ----
	@Override 
	public POReturnDetails updatePOReturn(POReturnDetails poret, int i) {
		logger.info("Update POReturn Number --->"+poret.getInvoicenumber());
		Update update = new Update();
		Query query = new Query();
		if(i == 1) {
			query.addCriteria(Criteria.where("invoicenumber").is(poret.getInvoicenumber()));
			update.set("paymentstatus", paymentstatus2);
			logger.debug("After POReturn Payment Status Update -->");
		}else if(i == 2) {
			query.addCriteria(Criteria.where("_id").is(poret.getId()));
			update.set("returnStatus", poret.getReturnStatus());
			update.set("itemStatus", poret.getItemStatus());
			update.set("qty", poret.getQty());
			update.set("price", poret.getPrice());
			update.set("status", poret.getStatus());
			update.set("paymentstatus", poret.getPaymentstatus());
			logger.debug("After POReturn Update -->");
		}
		mongoTemplate.findAndModify(query, update,
				new FindAndModifyOptions().returnNew(true), POReturnDetails.class);
		return poret; 
	}
	
	@Override
	public Template getTemplateDetails(String templatetype) {
		Template template;
		Query query = new Query();
		query.addCriteria(Criteria.where("templateType").is(templatetype));
		template = mongoTemplate.findOne(query, Template.class);
		return template;
	}
	
	//--- Insert Transaction Table ---
	public Template addTemplateDetails(Template template) {
		logger.info("DAO addTemplateDetails");
		Update update = null;
		Query query = null;
		try {
			query = new Query();		
			query.addCriteria(Criteria.where("templateType").is("Purchase Invoice"));
			List<Template> list = mongoTemplate.find(query,Template.class);
			if(list.size()>0) {
				// update
				update = new Update();
				query = new Query();
				query.addCriteria(Criteria.where("templateType").is("Purchase Invoice"));
				update.set("companyname", template.getCompanyname());
				update.set("address", template.getAddress());
				update.set("city", template.getCity());
				update.set("country", template.getCountry());
				update.set("templateType", "Purchase Invoice");
				update.set("companylogo", template.getCompanylogo());
				mongoTemplate.updateFirst(query, update, Template.class);
			} else {
	            // save
				mongoTemplate.save(template);
			} 
		}catch(Exception e) {
			e.printStackTrace();
		}
		return template;
	}
	
	public List<Template> getTemplateListDetails(List<Template> templist, String templatetype){
		Query query = new Query();
		query.addCriteria(Criteria.where("templateType").is(templatetype));
		templist = mongoTemplate.find(query, Template.class);
		return templist;
	}
	
	@Override 
	public List<Transaction> loadTransaction(List<Transaction> translist,String invoicenumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("invoicenumber").is(invoicenumber));
		translist = mongoTemplate.find(query, Transaction.class);
		return translist;
	}
	
	@Override 
	public Transaction updateTransaction(Transaction trans) {
		logger.info("Update POReturn Number --->"+trans.getInvoicenumber());
		Update update = new Update();
		Query query = new Query();
		query.addCriteria(Criteria.where("invoicenumber").is(trans.getInvoicenumber()));
		update.set("invoicenumber", trans.getInvoicenumber());
		update.set("credit", trans.getCredit());
		update.set("debit", trans.getDebit());
		mongoTemplate.findAndModify(query, update,
				new FindAndModifyOptions().returnNew(true), Transaction.class);
		return trans; 
	}
	
	// Remove Transaction
	public boolean removeTransaction(String invoicenumber) {
		logger.info("Transaction Invoice-->"+invoicenumber);
		Query query = new Query();
		query.addCriteria(Criteria.where("invoicenumber").is(invoicenumber));
		mongoTemplate.remove(query, Transaction.class);
		return true;
	}
	
	// Remove POReturn
	public boolean removePoReturn(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		mongoTemplate.remove(query, POReturnDetails.class);
		return true;
	}

	public List<RecentUpdates> loadRecentList(List<RecentUpdates> responseList){
		Query query = new Query();
		query.with(new Sort(new Order(Direction.DESC, "id"))).limit(10);
		responseList = mongoTemplate.find(query, RecentUpdates.class);
		return responseList;
	}
		
}
