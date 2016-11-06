package Demo.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import Demo.Meta.TwitterData;


public interface ITwitterDao extends MongoRepository<TwitterData, String> {

	@Query(value="{ 'id_str' : ?0 }")
	public TwitterData findOneById_str(String id_str);
				
}
