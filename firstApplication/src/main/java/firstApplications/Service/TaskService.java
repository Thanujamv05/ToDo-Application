package firstApplications.Service;

import java.util.List;

import firstApplications.Binding.TaskBinding;
import firstApplications.Entity.Task;
import firstApplications.Entity.Users;

public interface TaskService {
	Task savetask(TaskBinding taskbinding, Users  user);
	
	public List<Task> getTaskByUser(Users user);
	
	public Task getTaskByIdAndUsers(Long id, Users users);
	
	Task savetask(Task task,Users user);
	
	public void deleteTask(Task task);
	
}
