package src.test.chat.data;

import lombok.Data;

@Data
public class MessageVO {

	private String room_id;
	private String u_id;
	private String u_id_target;
	private String message;
}
