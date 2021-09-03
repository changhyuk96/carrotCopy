package src.test.web.controller;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class BoardController {

	@GetMapping("/board")
	public String board() {
		return "board";
	}
	
	@PostMapping("/boards")
	public String createBoards(@RequestParam MultipartFile[] uploadFile, boardDTO boardDTO) {
		
		for(MultipartFile file : uploadFile) {
			
			if(!file.isEmpty()) {
				
				boardDTO.setFileUUID(UUID.randomUUID().toString());
				boardDTO.setOriginalFileName(file.getOriginalFilename());
				boardDTO.setContentType(file.getContentType());
				
				File fileName = new File(boardDTO.getFileUUID() + "_" + boardDTO.getOriginalFileName());
				try {
					file.transferTo(fileName);
				} catch (IllegalStateException | IOException e) {
					e.printStackTrace();
				}
			}
			
		}

		return null;
		
	}
}
