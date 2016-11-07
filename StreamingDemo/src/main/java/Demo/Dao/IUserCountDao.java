package Demo.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import Demo.Meta.TwitCount;
import Demo.Meta.UserCount;

public interface IUserCountDao extends MongoRepository<UserCount, String>{

}
