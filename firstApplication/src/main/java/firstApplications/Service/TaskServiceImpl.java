package firstApplications.Service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import firstApplications.Binding.TaskBinding;
import firstApplications.Entity.Task;
import firstApplications.Entity.Users;
import firstApplications.Repository.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService{


	@Autowired
	private TaskRepository taskrepository;
	
	@Override
	public Task savetask(TaskBinding taskbinding,Users user) {
		Task task=new Task();
		BeanUtils.copyProperties(taskbinding,task);
		task.setUsers(user);
		return taskrepository.save(task);
		
	}

	public List<Task> getTaskByUser(Users user) {
		return taskrepository.findByUsers(user);
	}

	public Task getTaskByIdAndUsers(Long id, Users users) {
		return taskrepository.getByIdAndUsers(id,users);
		
	}

	public Task savetask(Task task, Users user) {
		task.setUsers(user);
		return taskrepository.save(task);
		
	}


	public void deleteTask(Task task) {
		taskrepository.delete(task);
	}
}
