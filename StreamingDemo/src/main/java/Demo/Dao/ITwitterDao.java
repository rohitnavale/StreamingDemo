package Demo.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import Demo.Meta.TwitterData;

public interface ITwitterDao extends MongoRepository<TwitterData, String> {
	
		
}
