package src.test.chat.data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.couchbase.client.core.error.DocumentNotFoundException;
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
	
	JSONParser parser = new JSONParser();
	
	// 구독했을 때 Couchbase에 저장.
	public void subscribe(MessageVO message) {
		
		bucketCheck();
		
	}
	
	// 메세지 Couchbase에 저장.
	public void sendMessage(MessageVO message) {
		
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
														.put("time", message.getTime())
														.put("u_id", message.getU_id())
														.put("message", message.getMessage()))
					)
					
				);
		}
		else {
			// subDocument에 message만 추가
			collection.mutateIn(message.getRoom_id(),
								Collections.singletonList(
										ArrayAppend.arrayAppend("message", Collections.singletonList(JsonObject.create()
												.put("time", message.getTime())
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
			QueryResult result = bucket.defaultScope().query("select room_id, ARRAY_EXCEPT(u_id,['"+u_id+"']) u_id, message[-1] message from `ChatBucket`._default._default where ARRAY_CONTAINS(u_id, '"+u_id+"') = true");
			for(JsonObject js : result.rowsAsObject()) {
				
				JSONObject json = new JSONObject();
			
				json.put("room_id", js.get("room_id").toString());
				json.put("u_id", ((JsonArray)js.get("u_id")).get(0));
				json.put("message", js.get("message").toString());
				
				jsonArray.add(json);
			}
		}catch(Exception e) {
			
			System.out.println(e.toString());
		}
		
		
		return jsonArray;
	}
	
	public Object getChatting(MessageVO message) {
		bucketCheck();
		
		try {
			JsonObject jsonObj;
			
			if(message.getRoom_id().equals("null")) {
				
				QueryResult result = bucket.defaultScope().query("select room_id from `ChatBucket`._default._default where ARRAY_CONTAINS(u_id, '"+message.getU_id()+"') = true and Array_contains(u_id, '"+message.getU_id_target()+"') = true");
				
				if(result.rowsAsObject().size() >= 1)
					message.setRoom_id(result.rowsAsObject().get(0).get("room_id").toString());
			}
			
			jsonObj = collection.get(message.getRoom_id()).contentAsObject();
			JSONObject json= null;
			JSONArray array = null;
		
			json = (JSONObject) parser.parse(jsonObj.toString());
			array = (JSONArray)json.get("message");
			
			return array;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch(DocumentNotFoundException e) {
			return "firstChatting";
		}
		
		return null;
	}

	public void bucketCheck() {
		
		if(collection ==null)
			collection = couchbaseConfig.getBucket().defaultCollection();
		if(bucket ==null)
			bucket = couchbaseConfig.getBucket();
	}
}
