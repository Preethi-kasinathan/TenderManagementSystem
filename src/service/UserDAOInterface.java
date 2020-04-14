package service;

import java.io.IOException;

import model.User;

public interface UserDAOInterface {

	
	int NewUser(User user) throws IOException, ClassNotFoundException, Exception;
	boolean Userlogin(User user) throws IOException, ClassNotFoundException, Exception;
	
}
