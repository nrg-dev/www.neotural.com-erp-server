package com.erp.mongo.dal;

import java.util.List;

import com.erp.dto.Purchase;
import com.erp.mongo.model.Item;
import com.erp.mongo.model.POInvoice;
import com.erp.mongo.model.POInvoiceDetails;
import com.erp.mongo.model.POReturnDetails;
import com.erp.mongo.model.PurchaseOrder;
import com.erp.mongo.model.RecentUpdates;
import com.erp.mongo.model.Template;
import com.erp.mongo.model.Vendor;
import com.erp.mongo.model.Transaction;

public interface PurchaseDAL {
	public POInvoice savePOInvoice(POInvoice poinvoice);

//	public POInvoiceDetails savePurchase(POInvoiceDetails purchaseorder);

	//public List<POInvoice> loadPurchase(List<POInvoice> list);
	public List<POInvoice> loadInvoice(String paystatus, String invoicenumber);  

	public List<POInvoiceDetails> getPurchase(String id);

	public List<Vendor> loadVendorList(List<Vendor> response);

	public Vendor getVendorDetails(String id);

	public String removePurchase(String invoiceNumber);

	public String removePartId(String id, String invoiceNumber, int temp);

	public List<Item> loadItem(String categoryCode);

	public Item getUnitPrice(String productCode, String categoryCode);

	public POInvoiceDetails updatePurchase(POInvoiceDetails purchase);

	public POReturnDetails insertReturn(POReturnDetails poreturndetails);
	
	public POInvoice updatePOInvoice(POInvoice poinvoice, int i);
	
	public POInvoice loadPOInvoice(String invoicenumber);

	public List<Item> loadVendorItem(List<Item> itemlist, String vendorCode);

	public List<POInvoice> loadfilterData(List<POInvoice> response,String fromdate, String todate); 
	
	public List<PurchaseOrder> loadPO(int i,String type);
	
	public PurchaseOrder savePO(PurchaseOrder purchaseorder);
	public boolean removePO(String id);
	public boolean updatePurchaseOrder(PurchaseOrder purchaseorder, int i); 
	public boolean updatePO(String invoice,String[] value);

	public List<POReturnDetails> loadReturn(String paystatus, String pocode); 

	public Transaction saveTransaction(Transaction tran);

	public POReturnDetails updatePOReturn(POReturnDetails poret, int i); 

	public Template getTemplateDetails(String templatetype);

	public Template addTemplateDetails(Template template);

	public List<Template> getTemplateListDetails(List<Template> templist, String templatetype);

	public List<Transaction> loadTransaction(List<Transaction> translist,String invoicenumber);
	public Transaction updateTransaction(Transaction trans);

	public boolean removeTransaction(String invoicenumber);

	public boolean removePoReturn(String id);

	public List<RecentUpdates> loadRecentList(List<RecentUpdates> responseList);        

}