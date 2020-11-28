package com.erp.mongo.dal;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.erp.mongo.model.Customer;
import com.erp.mongo.model.PettyCash;
import com.erp.mongo.model.Transaction;
import com.erp.mongo.model.Vendor;
import com.erp.util.Custom;

@Repository
public class FinanceImpl implements FinanceDAL {

	public static final Logger logger = LoggerFactory.getLogger(FinanceImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	// load customer and vendor name & code
	public ArrayList<String> loadCustomerVendorName() {
		ArrayList<String> list = new ArrayList<String>();
		List<Customer> customerlist = mongoTemplate.findAll(Customer.class);
		List<Vendor> vendorlist = mongoTemplate.findAll(Vendor.class);
		for (Customer customer : customerlist) {
			logger.debug("Customer name-->" + customer.getCustomerName());
			logger.debug("Customer code-->" + customer.getCustcode());
			list.add(customer.getCustomerName() + "-" + customer.getCustcode());
		}
		for (Vendor vendor : vendorlist) {
			logger.debug("Vendor name-->" + vendor.getVendorName());
			logger.debug("Vendor code-->" + vendor.getVendorcode());
			list.add(vendor.getVendorName() + "-" + vendor.getVendorcode());
		}
		return list;
	}

	// save
	public PettyCash save(PettyCash finance) {
		mongoTemplate.save(finance);
		return finance;
	}

	// load petty cash list
	public List<PettyCash> load() {
		List<PettyCash> pettycashlist = new ArrayList<PettyCash>();
		Query query = new Query();
	    query.with(new Sort(new Order(Direction.DESC, "invoicenumber")));
	    pettycashlist = mongoTemplate.find(query,PettyCash.class);
		//List<PettyCash> pettycashlist = mongoTemplate.findAll(PettyCash.class);
		return pettycashlist;
	}

	public PettyCash updatePettyCash(PettyCash pettycash) {
		Update update = new Update();
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(pettycash.getId()));
		update.set("description", pettycash.getDescription());
		update.set("addedDate", pettycash.getAddedDate());
		update.set("type", pettycash.getType());
		update.set("toPerson", pettycash.getToPerson());
		update.set("totalAmount", pettycash.getTotalAmount());
		update.set("currency", pettycash.getCurrency());
		update.set("invoicenumber", pettycash.getInvoicenumber());
		update.set("status", "Active");
		mongoTemplate.updateFirst(query, update, PettyCash.class);
		return pettycash;
	}

	public PettyCash removePettyCash(String id) {
		PettyCash response = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("id").is(id));
		mongoTemplate.remove(query, PettyCash.class);
		return response;
	}
	
	@Override
	public List<Transaction> loadProfitLoss() {
		List<Transaction> list = new ArrayList<Transaction>();
		Query query = new Query();
	    query.with(new Sort(new Order(Direction.DESC, "transactionnumber")));
		list = mongoTemplate.find(query,Transaction.class);
		return list;
	}
	
	//----- Load Profit and Loss Based on date --
	public List<Transaction> loadfilterProfitData(List<Transaction> list,String fromdate, String todate) throws ParseException {
		Query query = new Query();
		if(fromdate.equalsIgnoreCase(todate)) {
			logger.debug("-------- Both Dates are Equal --------");
			String transdate = Custom.convertStringToData(fromdate); 
			logger.debug("Transaction Date -->"+transdate);
		    query.with(new Sort(new Order(Direction.DESC, "transactionnumber")));
			query.addCriteria(Criteria.where("transactiondate").is(transdate));
			list = mongoTemplate.find(query,Transaction.class);
		}else {
			logger.debug("-------- Both Dates are Not Equal --------");
			String transfromdate = Custom.convertStringToData(fromdate);
			String transtodate = Custom.convertStringToData(todate);
			/* db.getCollection('user').find({
				transactiondate: {
			        $gt: ISODate(transfromdate),
			        $lt: ISODate(transtodate)
			    }
			}); */
		    query.with(new Sort(new Order(Direction.DESC, "transactionnumber")));
		    //query.addCriteria(new Criteria().andOperator(Criteria.where("transactiondate").gte(transfromdate),
                //Criteria.where("transactiondate").lte(transtodate)));
			query.addCriteria(Criteria.where("transactiondate").lte(transtodate).gte(transfromdate));
			list = mongoTemplate.find(query,Transaction.class);
			for(Transaction t : list) {
				logger.debug("Invoice Number -->"+t.getInvoicenumber());
			}
			logger.info("Filter List Size -->"+list.size()); 
		}	
		return list;
	}
	
	//----------- Update Transaction Table PettyCash Details --------
	public Transaction updatePettyTransaction(Transaction tran) {
		Update update = new Update();
		Query query = new Query();
		try {
			query.addCriteria(Criteria.where("invoicenumber").is(tran.getInvoicenumber()));
			update.set("credit", tran.getCredit());
			update.set("debit", tran.getDebit());
			mongoTemplate.findAndModify(query, update,
					new FindAndModifyOptions().returnNew(true), Transaction.class);
		}catch(Exception e) {
			logger.info("Transaction Petty Update Exception --->"+e.getMessage()); 
		}
		
		return tran;
	}
					

}
