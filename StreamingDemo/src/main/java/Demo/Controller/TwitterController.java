package Demo.Controller;

import javax.servlet.http.HttpServletRequest;

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
	
	@RequestMapping(value = "/getAll", method = RequestMethod.GET)
	public @ResponseBody String getAll() throws JsonProcessingException {
		return twitterServiceImpl.getAll();
	}
	
	@RequestMapping(value = "/getUserByData", method = RequestMethod.GET)
	public @ResponseBody String getUserByData(@RequestParam("TwitId") String twitId) throws JsonProcessingException {
		return twitterServiceImpl.getUserBydata(twitId);
	}
	
	/*@RequestMapping(value = "/getTwitcount", method = RequestMethod.GET)
	public @ResponseBody String getTwitcount(@RequestParam("UserId") String userId)
			throws JsonProcessingException {	
		return twitterServiceImpl.getTwitcount(userId);
	}
	*/
	
	/*@RequestMapping(value = "/getTwitcount", method = RequestMethod.GET)
	public @ResponseBody Integer getTwitcount(@RequestParam("Id_str") String id_str)
			throws JsonProcessingException {	
		return twitterServiceImpl.getTwitcount(id_str);
	}*/
	
	@RequestMapping(value = "/getTwitcount", method = RequestMethod.GET)
	public @ResponseBody String getTwitcount(@RequestParam("Id_str") String id_str)
			throws JsonProcessingException {	
		return twitterServiceImpl.getTwitcount(id_str);
	}
	
	@RequestMapping(value = "/getDatabyidstr", method = RequestMethod.GET)
	public @ResponseBody String getDatabyidstr(@RequestParam("UserId") String userId)
			throws JsonProcessingException {	
		return twitterServiceImpl.getdatabyidstr(userId);
	}
	
}
