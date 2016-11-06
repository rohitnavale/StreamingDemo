package Demo.Meta;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection="TwitCount")
public class TwitCount {
	private String id;
	private String name = "Twit";
	private Integer count;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}

}
