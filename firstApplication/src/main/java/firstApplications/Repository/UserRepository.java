package firstApplications.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import firstApplications.Binding.TaskBinding;
import firstApplications.Entity.Users;

public interface UserRepository extends JpaRepository<Users,String> {

    Users findByEmailAndPassword(String email, String password);
    
	Users findByEmail(String email);

	boolean existsByEmail(String email);

	



}
