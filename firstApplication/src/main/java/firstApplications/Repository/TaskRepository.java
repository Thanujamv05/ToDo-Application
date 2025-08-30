package firstApplications.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import firstApplications.Entity.Task;
import firstApplications.Entity.Users;

public interface TaskRepository extends JpaRepository<Task,Long> {

	List<Task> findByUsers(Users users);

    Task getByIdAndUsers(Long id, Users users);

	

}
