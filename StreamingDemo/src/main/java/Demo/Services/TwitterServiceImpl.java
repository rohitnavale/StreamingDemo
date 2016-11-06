package Demo.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import Demo.Dao.ITwitterDao;
import Demo.Meta.TwitterData;
import Demo.Meta.User;

@Service
public class TwitterServiceImpl{

	@Autowired 
	ITwitterDao iTwitterDao;
	
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

}
