package src.test.chat.data;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.java.Bucket;
import com.couchbase.client.java.Collection;
import com.couchbase.client.java.json.JsonArray;
import com.couchbase.client.java.json.JsonObject;
import com.couchbase.client.java.kv.ArrayAppend;
import com.couchbase.client.java.query.QueryOptions;
import com.couchbase.client.java.query.QueryResult;

import src.test.chat.controller.config.CouchbaseConfig;

@Service
public class CouchbaseRepository {

	@Autowired
	private CouchbaseConfig couchbaseConfig;
	private Bucket bucket;
	private Collection collection;
	
	// 구독했을 때 Couchbase에 저장.
	public void subscribe(MessageVO message) {
		
		bucketCheck();
		
		// 초기 방 생성.
		// Room_id의 방이 존재하지 않으면 새로 생성합니다.
		if(!collection.exists(message.getRoom_id()).exists()) {
			
			collection.insert(
					// key :: room_id
					message.getRoom_id(),
					// value :: JsonObject. 
					JsonObject.create()
					.put("room_id", message.getRoom_id())
					.put("u_id", JsonArray.create().add(message.getU_id()).add(message.getU_id_target()))
					.put("message", JsonArray.create().add(JsonObject.create()
														.put("time", LocalDateTime.now().toString())
														.put("u_id", message.getU_id())
														.put("message", message.getMessage()))
					)
					
				);
		}
	}
	
	// 메세지 Couchbase에 저장.
	public void sendMessage(MessageVO message) {
		
		bucketCheck();
		
		if(collection.exists(message.getRoom_id()).exists()) {
			
			// subDocument에 message만 추가
			collection.mutateIn(message.getRoom_id(),
								Collections.singletonList(
										ArrayAppend.arrayAppend("message", Collections.singletonList(JsonObject.create()
												.put("time", LocalDateTime.now().toString())
												.put("u_id", message.getU_id())
												.put("message", message.getMessage())))
										)
						);
		}
		
	}
	
	// 본인이 소속된 방 리스트 확인
	@SuppressWarnings("unchecked")
	public Object userChatroomList(String u_id) {
		bucketCheck();
		
		JSONArray jsonArray = new JSONArray();
		
		
		try {
			QueryResult result = bucket.defaultScope().query("select room_id, ARRAY_EXCEPT(u_id,['"+u_id+"']) u_id from `ChatBucket`._default._default where ARRAY_CONTAINS(u_id, '"+u_id+"') = true",
															QueryOptions.queryOptions().parameters(JsonArray.from(u_id).add(u_id)));
			for(JsonObject js : result.rowsAsObject()) {
				
				JSONObject json = new JSONObject();
			
				json.put("room_id", js.get("room_id").toString());
				json.put("u_id", ((JsonArray)js.get("u_id")).get(0));
				
				jsonArray.add(json);
			}
		}catch(Exception e) {
			
			System.out.println(e.toString());
		}
		
		
		return jsonArray;
	}

	public void bucketCheck() {
		
		if(collection ==null)
			collection = couchbaseConfig.getBucket().defaultCollection();
		if(bucket ==null)
			bucket = couchbaseConfig.getBucket();
	}
}
