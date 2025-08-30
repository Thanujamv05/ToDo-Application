package firstApplications.Service;

import firstApplications.Binding.TaskBinding;
import firstApplications.Binding.UsersBinding;
import firstApplications.Entity.Task;
import firstApplications.Entity.Users;

public interface UserService {

	
	public Users registerUser(UsersBinding binding);
	
	boolean checkEmail(String email);

	Users getUserForSession(String email, String password);

	Users getUserByEmail(String email);

	

	
}
