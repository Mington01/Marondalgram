package com.en3873.marondalgram.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.en3873.marondalgram.common.EncryptUtils;
import com.en3873.marondalgram.user.dao.UserDAO;
import com.en3873.marondalgram.user.model.User;

@Service
public class UserBO {
	
	@Autowired
	private UserDAO userDAO;
	
	public int addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		return userDAO.insertUser(loginId, encryptPassword, name, email);
		
	}
	
	public boolean duplicateId(String loginId) {
		
		int count = userDAO.selectCountByLoginId(loginId);
		
//		if(count == 0) {
//			return false;
//		} else {
//			return true;
//		}
		
		return count != 0;
		
	}
	
public User getUser(String loginId, String password) {
		
		String encryptPassword = EncryptUtils.md5(password);
		
		return userDAO.selectUser(loginId, encryptPassword);
	}
	
	public User getUserById(int id) {
		return userDAO.selectUserById(id);
	}

}