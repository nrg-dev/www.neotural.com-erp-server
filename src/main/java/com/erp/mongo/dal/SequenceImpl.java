package com.erp.mongo.dal;

import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import com.erp.mongo.model.DatabaseSequence;


@Repository
public class SequenceImpl   implements SequenceDAL {

	public static final Logger logger = LoggerFactory.getLogger(SequenceImpl.class);

	@Autowired
	private MongoTemplate mongoTemplate;	



    public long generateSequence(String seqName) {

    	DatabaseSequence counter = mongoTemplate.findAndModify(query(where("sequencename").is(seqName)),
                new Update().inc("seq",1), options().returnNew(true).upsert(true),
                DatabaseSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;

    }

}
