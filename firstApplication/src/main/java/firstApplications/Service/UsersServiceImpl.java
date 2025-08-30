package firstApplications.Service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import firstApplications.Binding.TaskBinding;
import firstApplications.Binding.UsersBinding;
import firstApplications.Entity.Task;
import firstApplications.Entity.Users;
import firstApplications.Repository.TaskRepository;
import firstApplications.Repository.UserRepository;
@Service
public class UsersServiceImpl  implements UserService{

	@Autowired
	private UserRepository userrepository;
	@Autowired
	private TaskRepository taskrepository;
	
	@Override
	public Users registerUser(UsersBinding binding) {
    
			Users entity=new Users();
			BeanUtils.copyProperties(binding, entity);
			return userrepository.save(entity);
	}

	@Override
    public Users getUserForSession(String email, String password) {
        return userrepository.findByEmailAndPassword(email, password);
                                                        
    }
	
	@Override
	public boolean checkEmail(String email) {
		
		return userrepository.existsByEmail(email);
	}

	@Override
	public Users getUserByEmail(String email)
	{
		return userrepository.findByEmail(email);
	}
}
	