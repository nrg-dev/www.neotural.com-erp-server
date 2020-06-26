package com.mynrg.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.mynrg.model.ProductionIssue;

//@Repository
//@Transactional
//@Service(value="b")
public interface IssuesInterface  {
	//public interface IssuesInterface extends org.springframework.data.repository.Repository<ProductionIssue,Integer> {

	/*
	 * @Query("SELECT issueId, issueNotes,clientName,status,priority FROM ProductionIssue"
	 * ) // JPA projection using query List<ProductionIssue> getProductionIssues();
	 */

}
