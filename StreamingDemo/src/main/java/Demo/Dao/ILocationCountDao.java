package Demo.Dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import Demo.Meta.LocationCount;
import Demo.Meta.TwitCount;

public interface ILocationCountDao extends MongoRepository<LocationCount, String> {

}
