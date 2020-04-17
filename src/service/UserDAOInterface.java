package service;

import java.io.IOException;
import java.util.List;

import model.TenderRequest;
import model.Tender_Authority;
import model.User;

public interface UserDAOInterface {

	
	int NewUser(User user) throws IOException, ClassNotFoundException, Exception;
	boolean Userlogin(User user) throws IOException, ClassNotFoundException, Exception;
	boolean Authoritylogin(Tender_Authority tndr_autry) throws IOException, ClassNotFoundException, Exception;
	List<TenderRequest> selectallrequest() throws ClassNotFoundException, Exception;
}
