package firstApplications.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import firstApplications.Binding.TaskBinding;
import firstApplications.Binding.UsersBinding;
import firstApplications.Entity.Task;
import firstApplications.Entity.Users;
import firstApplications.Repository.UserRepository;
import firstApplications.Service.TaskServiceImpl;
import firstApplications.Service.UsersServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpSessionEvent;

@Controller
public class UserController {

   

	@Autowired
	private UsersServiceImpl userservice;
	
	@Autowired
	private TaskServiceImpl taskservice;
	
	@GetMapping({"/", "/home"})
    public String home() {
        return "index"; 
	}
    
	@GetMapping("/register")
	public String showRegisterPage(Model model)
	{
		model.addAttribute("user", new UsersBinding());
		return "Register";
	}
	
	@PostMapping("/rpage")
	public String saveUser(@ModelAttribute("user") UsersBinding binding,
	                       RedirectAttributes redirectAttributes) {
		
		if (!binding.getPassword().equals(binding.getConfirmpassword())) {
		        redirectAttributes.addFlashAttribute("error", "Password and Confirm Password do not match!");
		        return "redirect:/register";
		    }
		    
	    boolean f = userservice.checkEmail(binding.getEmail());

	    if (f) {
	        redirectAttributes.addFlashAttribute("msg", "Email id Already exist");
	    } else {
	        Users saved = userservice.registerUser(binding);

	        if (saved != null) {
	            redirectAttributes.addFlashAttribute("msg", "Registration successful! Login now.");
	        } else {
	            redirectAttributes.addFlashAttribute("error", "Registration failed");
	        }
	    }

	    return "redirect:/register"; // Post/Redirect/Get
	}
	
	@GetMapping("/signin")
 	public String showLoginPage(Model model) {
		model.addAttribute("user", new UsersBinding());
		return "Login";
	}
	
	@PostMapping("/lpage")
	 public String loginUser(@ModelAttribute("user") UsersBinding binding,RedirectAttributes redirectAttributes,HttpSession session) {
		String email=binding.getEmail();
		String password=binding.getPassword();
		
		Users valid=userservice.getUserForSession(email, password);
		if(valid!=null)
		{
			session.setAttribute("email",valid.getEmail());
			session.setAttribute("password", valid.getPassword());
			session.setAttribute("username", valid.getUsername());
			return "Profile";
		}
		
		else {
			redirectAttributes.addFlashAttribute("msg", "Invalid Email or Password");
			return "redirect:/signin";
		}
		
            }
//	@GetMapping("/signout")
//	public String logout(HttpSession session,Model model) {
//	    session.invalidate();
//	    model.addAttribute("msg", "You have been logged out successfully!");
//	    model.addAttribute("user", new Users());
//	    return "Login";
//	}

@GetMapping("/signout")
public String logout(HttpSession session,RedirectAttributes redirectattributes)
{
	session.invalidate();
	redirectattributes.addFlashAttribute("msg","You have been logged out successfully!!!");
	return "redirect:/signin";
	
}
	
}