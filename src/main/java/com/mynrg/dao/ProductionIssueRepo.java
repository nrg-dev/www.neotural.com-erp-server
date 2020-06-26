package com.mynrg.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;

import com.mynrg.model.ProductionIssue;

public interface ProductionIssueRepo extends  org.springframework.data.repository.Repository<ProductionIssue,Integer>{
    @Query("SELECT issueId, issueNotes,clientName,status,priority FROM ProductionIssue" )
	public List<ProductionIssue> loadIssues();
}
