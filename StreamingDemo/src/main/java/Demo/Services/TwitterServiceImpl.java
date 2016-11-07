package Demo.Services;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.group;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.newAggregation;

import java.util.ArrayList;
import java.util.List;

import org.apache.hadoop.hive.serde2.io.TimestampWritable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.aggregation.Aggregation.match;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import Demo.Dao.ILocationCountDao;
import Demo.Dao.ITwitCountDao;
import Demo.Dao.ITwitterDao;
import Demo.Dao.IUserCountDao;
import Demo.Meta.LocationCount;
import Demo.Meta.TwitCount;
import Demo.Meta.TwitterData;
import Demo.Meta.User;
import Demo.Meta.UserCount;

@Service
public class TwitterServiceImpl{

	@Autowired 
	ITwitterDao iTwitterDao;
	@Autowired
	IUserCountDao iUsercountDao;
	@Autowired
	ILocationCountDao iLocationCountDao;
	@Autowired
	ITwitCountDao iTwitCountDao;
	@Autowired
	MongoTemplate mongoTemplate;
	
	public TwitterData findOneById(String id){
		return iTwitterDao.findOne(id);
	}
	
	public String getUserBydata(String twitId) throws JsonProcessingException {
		
		String result = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();					
		TwitterData twitterDataDO = findOneById(twitId);
		User UserDO = twitterDataDO.getUser();
		result = ow.writeValueAsString(UserDO);
		return result;
	}

	public String getOneById(String id) throws JsonProcessingException {
	
		String result = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		result = ow.writeValueAsString(findOneById(id));
		return result;
	}

	public String getAll() throws JsonProcessingException {
		String result = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		result = ow.writeValueAsString(iTwitterDao.findAll());
		return result;
	}

	/*public String getTwitcount(String userId) throws JsonProcessingException {
		
		String finalResult = null;
		Integer count = 0;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Aggregation twitAggr = newAggregation(match(Criteria.where("id_str").equals(userId)),group("id_str").count().as("count"),sort(Sort.Direction.DESC, "count"));
		AggregationResults<TwitterData> twitResults = mongoTemplate.aggregate(twitAggr, "TwitterData", TwitterData.class);
		List<TwitterData> twitList = twitResults.getMappedResults();
		List<TwitterData> twitList = iTwitterDao.findAll();
		List<TwitterData> result = new ArrayList<TwitterData>();
		for (TwitterData t : twitList) {
			TwitterData twitLatest = iTwitterDao.findOneById_str(userId);			
			//twitLatest.setCount(count);
			result.add(twitLatest);
		}	
		
		return ow.writeValueAsString(result);
	}*/
	
public String getTwitcount(String id_str) throws JsonProcessingException {
		
		String finalResult = null;
		Integer count = 0;
		TwitCount twitCount = new TwitCount();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Aggregation twitAggr = newAggregation(match(Criteria.where("id_str").is(id_str)));
		AggregationResults<TwitterData> twitResults = mongoTemplate.aggregate(twitAggr, "TwitterData", TwitterData.class);
		List<TwitterData> twitList = twitResults.getMappedResults();
		//List<TwitterData> twitList = iTwitterDao.findAll();
		List<TwitCount> result = new ArrayList<TwitCount>();
		for (TwitterData t : twitList) {
			
			TwitterData twitLatest = iTwitterDao.findOneById_str(t.getId_str());
			twitCount.setId(twitLatest.getId_str());
			twitCount.setName(twitLatest.getText());
			if(twitLatest!=null)
			{
				count++;
			}			
			twitCount.setCount(count);				
		}	
		//result.add(twitCount);
		return ow.writeValueAsString(twitCount);
	}

	public String getdatabyidstr(String userId) throws JsonProcessingException {
		String finalResult = null;
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		finalResult = ow.writeValueAsString(iTwitterDao.findOneById_str(userId)) ;

		return finalResult;
	}

	public String getUserCount(String id_str) throws JsonProcessingException {
		
		Integer count = 0;
		UserCount userCount = new UserCount();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Aggregation twitAggr = newAggregation(match(Criteria.where("user.id_str").is(id_str)));
		AggregationResults<TwitterData> twitResults = mongoTemplate.aggregate(twitAggr, "TwitterData", TwitterData.class);
		List<TwitterData> twitList = twitResults.getMappedResults();
		//List<TwitterData> twitList = iTwitterDao.findAll();
		List<UserCount> result = new ArrayList<UserCount>();
		for (TwitterData t : twitList) {
			
			TwitterData twitLatest = iTwitterDao.findOneById_str(t.getId_str());
			userCount.setId(twitLatest.getUser().getId_str());
			userCount.setName(twitLatest.getUser().getName());
			if(twitLatest!=null)
			{
				count++;
			}			
			userCount.setCount(count);
			//result.add(userCount);		
		}	
		
		return ow.writeValueAsString(userCount);
	}

	public String getLocationCount(String location) throws JsonProcessingException {
		Integer count = 0;
		UserCount locationCount = new UserCount();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		Aggregation twitAggr = newAggregation(match(Criteria.where("user.location").is(location)));
		AggregationResults<TwitterData> twitResults = mongoTemplate.aggregate(twitAggr, "TwitterData", TwitterData.class);
		List<TwitterData> twitList = twitResults.getMappedResults();
		//List<TwitterData> twitList = iTwitterDao.findAll();
		List<LocationCount> result = new ArrayList<LocationCount>();
		for (TwitterData t : twitList) {
			
			TwitterData twitLatest = iTwitterDao.findOneById_str(t.getId_str());
			//locationCount.setId(twitLatest.getUser().getId_str());
			locationCount.setName(twitLatest.getUser().getLocation());
			if(twitLatest!=null)
			{
				count++;
			}			
			locationCount.setCount(count);
			//result.add(userCount);		
		}	
		
		return ow.writeValueAsString(locationCount);	
	}

	/*public String getCounter() {
		
		UserCount locationCount = new UserCount();
		ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
		List<UserCount> userCountDO = iUsercountDao.findAll();
		List<LocationCount> locationCountDO = iLocationCountDao.findAll();
		List<TwitCount> twitCount = iTwitCountDao.findAll();
		Aggregation twitAggr = newAggregation(match(Criteria.where("user.location").is(location)));
		AggregationResults<TwitterData> twitResults = mongoTemplate.aggregate(twitAggr, "TwitterData", TwitterData.class);
		List<TwitterData> twitList = twitResults.getMappedResults();
		//List<TwitterData> twitList = iTwitterDao.findAll();
		List<LocationCount> result = new ArrayList<LocationCount>();
		for (TwitterData t : twitList) {
			
			TwitterData twitLatest = iTwitterDao.findOneById_str(t.getId_str());
			//locationCount.setId(twitLatest.getUser().getId_str());
			locationCount.setName(twitLatest.getUser().getLocation());
			if(twitLatest!=null)
			{
				count++;
			}			
			locationCount.setCount(count);
			//result.add(userCount);		
		}	
		
		return ow.writeValueAsString(locationCount);
	}*/

}
