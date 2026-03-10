package fieldworks.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import fieldworks.exception.CustomException;
import fieldworks.models.Employee;
import fieldworks.services.EmployeeCrudService;
import fieldworks.services.EmployeePageService;
import fieldworks.services.EmployeeService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/employee/page")
public class EmployeePageController {

	@Autowired
	private EmployeePageService service;

	/* LOAD PAGE */
	@GetMapping
	public String page(Model model, HttpSession session) {

		if (session.getAttribute("loggedUser") == null) {
			model.addAttribute("view", "login");
			return "employee";
		}

		model.addAttribute("view", "crud");
		model.addAttribute("employee", new Employee());
		model.addAttribute("list", service.getAll());
		return "employee";
	}

	/* LOGIN */
	@PostMapping("/login")
	public String login(@RequestParam String username, @RequestParam String password, Model model,
			HttpSession session) {

		try {
			Employee emp = service.login(username, password);
			session.setAttribute("loggedUser", emp);
			return "redirect:/employee/page";

		} catch (CustomException ex) {
			model.addAttribute("error", ex.getMessage());
			model.addAttribute("view", "login");
			return "employee";
		}
	}

	/* LOGOUT */
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/employee/page";
	}

	/* SAVE / UPDATE */
	@PostMapping("/save")
	public String save(@ModelAttribute Employee employee) {
		service.save(employee);
		return "redirect:/employee/page";
	}

	/* EDIT */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable Long id, Model model) {

		model.addAttribute("view", "crud");
		model.addAttribute("employee", service.getById(id));
		model.addAttribute("list", service.getAll());
		return "employee";
	}

	/* DELETE */
	@GetMapping("/delete/{id}")
	public String delete(@PathVariable Long id) {
		service.delete(id);
		return "redirect:/employee/page";
	}
}