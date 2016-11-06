package Demo.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonProcessingException;

import Demo.Services.TwitterServiceImpl;

@Controller
@RequestMapping(value = "twitter/data")
public class TwitterController {

	@Autowired
	TwitterServiceImpl twitterServiceImpl;
	
	@RequestMapping(value = "/getOneById", method = RequestMethod.GET)
	public @ResponseBody String getOneById(@RequestParam("id") String id)
			throws JsonProcessingException {
		return twitterServiceImpl.getOneById(id);
	}
	
	@RequestMapping(value = "/getUserByData", method = RequestMethod.GET)
	public @ResponseBody String getUserByData(@RequestParam("TwitId") String TwitId) throws JsonProcessingException {
		return twitterServiceImpl.getUserBydata(TwitId);
	}
}
