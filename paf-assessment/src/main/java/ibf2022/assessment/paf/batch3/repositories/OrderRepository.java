package ibf2022.assessment.paf.batch3.repositories;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import ibf2022.assessment.paf.batch3.models.Order;

@Repository
public class OrderRepository {

	@Autowired
	private MongoTemplate mongo;
	
	private static final String COLLECTION_ORDERS = "orders";
	// TODO: Task 5
	public void insertOrder(Order o) {
		mongo.insert(Document.parse(o.toJsonString()), COLLECTION_ORDERS);
	}
}
