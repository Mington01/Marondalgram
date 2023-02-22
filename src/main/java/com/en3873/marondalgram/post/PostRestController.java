package com.en3873.marondalgram.post;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.en3873.marondalgram.post.bo.PostBO;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/post")
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	@PostMapping("/create")
	public Map<String, String> creat(
			@RequestParam("content") String content
			, @RequestParam("file") MultipartFile file
			, HttpSession session) {
		
			int userId = (Integer)session.getAttribute("userId");
			
			int count = postBO.addPost(userId, content, file);
			
			Map<String, String> result = new HashMap<>();
			
			if(count == 1) {
				result.put("result", "success");
			} else {
				result.put("result", "fail");
			}
			
			return result;
	}
	
	
	

}