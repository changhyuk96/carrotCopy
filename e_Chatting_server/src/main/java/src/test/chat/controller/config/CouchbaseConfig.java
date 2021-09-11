package src.test.chat.controller.config;

import org.springframework.context.annotation.Configuration;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Cluster;

@Configuration
public class CouchbaseConfig {

	private Cluster cluster;
	private Bucket bucket;
	
	private String url = "172.30.1.34";
	private String username ="Administrator";
	private String password = "123123";
	
	public CouchbaseConfig() {
		cluster = Cluster.connect(url, username, password);
		bucket = cluster.bucket("ChatBucket");
	}
	
	public Bucket getBucket() {
		return bucket;
	}
}
