package Demo.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import Demo.Meta.TwitCount;
import Demo.Meta.TwitterData;

public interface ITwitCountDao extends MongoRepository<TwitCount, String>{

}
