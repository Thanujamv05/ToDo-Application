package firstApplications.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import firstApplications.Binding.TaskBinding;
import firstApplications.Entity.Task;
import firstApplications.Entity.Users;
import firstApplications.Repository.TaskRepository;
import firstApplications.Service.TaskServiceImpl;
import firstApplications.Service.UsersServiceImpl;
import jakarta.servlet.http.HttpSession;

@Controller
public class TaskController {

	@Autowired
    private TaskRepository taskRepository;
	
	@Autowired
	private  UsersServiceImpl userservice;
	@Autowired
	private TaskServiceImpl taskservice;

   @GetMapping("/ctask")
	public String showCreateTaskPage(Model model)
	{
		model.addAttribute("task", new TaskBinding());
		return "Ctask";
	}

	@PostMapping("/cctask")
	public String savingTheTask(@ModelAttribute("task") TaskBinding taskbinding,HttpSession session,RedirectAttributes redirectattributes,Model model)
	{
		String email=(String)session.getAttribute("email");
		Users entity=userservice.getUserByEmail(email);
		if(entity!=null)
	{
		taskservice.savetask(taskbinding,entity);
		model.addAttribute("msg","Task Saved");
		model.addAttribute("user",new Users());
		return "Profile";
		
	}
		else
		{
			redirectattributes.addFlashAttribute("msg","User Not found:Register First");
			return "redirect:/ctask";
		}
	}
	@GetMapping("/vtask")
	public String showViewtaskpage(Model model,HttpSession session){
		String email=(String)session.getAttribute("email");
		Users user=userservice.getUserByEmail(email);
		if(user!=null)
		{
			List<Task> taskList=taskservice.getTaskByUser(user);
			model.addAttribute("tasks",taskList);
		}
		
		return "Vtask";
	}
	
	@GetMapping("/utask/{id}")
	public String showUpdatepage(@PathVariable("id") Long id,Model model,HttpSession session )
	{
		String email=(String)session.getAttribute("email");
		Users user=userservice.getUserByEmail(email);
		if(user!=null)
		{
			Task task=taskservice.getTaskByIdAndUsers(id,user);
			if(task!=null)
			{
				model.addAttribute("task", task);
				return "Utask";
			}
		}
				model.addAttribute("msg", "task not found");
				return"redirect:/vtask";
			
	}
	
	@PostMapping("/uutask")
	public String updateTask(@ModelAttribute("task") Task task,HttpSession session,RedirectAttributes redirectattributes) {
		String email=(String) session.getAttribute("email");
		Users user=userservice.getUserByEmail(email);
		
		if(user!=null)
		{
			taskservice.savetask(task,user);
			redirectattributes.addFlashAttribute("msg", "Task updated successfully!");
		}
		return "redirect:/vtask";
	}
	
	@GetMapping("/dtask/{id}")
	public String deletetask(@PathVariable("id") Long id, HttpSession session, RedirectAttributes redirectAttributes) {
	    String email = (String) session.getAttribute("email");
	    Users user = userservice.getUserByEmail(email);

	    if (user != null) {
	        Task task = taskservice.getTaskByIdAndUsers(id, user);
	        if (task != null) {
	            taskservice.deleteTask(task);
	            redirectAttributes.addFlashAttribute("msg", "Task deleted successfully!");
	        } else {
	            redirectAttributes.addFlashAttribute("msg", "Task not found!");
	        }
	    }
	    return "redirect:/vtask"; 
	}
}


