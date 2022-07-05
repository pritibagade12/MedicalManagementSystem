package com.example.medical.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.medical.Entity.Admin;
import com.example.medical.Exception.AdminNotFoundException;
import com.example.medical.Service.AdminService;



@RestController
@RequestMapping("/api")
public class AdminRestController {

	private AdminService adminService;
	
	@Autowired
	public AdminRestController(AdminService theAdminService) {
		adminService=theAdminService;
	}
	
	@GetMapping("/admins")
	public List<Admin> findAll(){
		return adminService.findAll();
	}
	
	@GetMapping("/admins/{adminId}")
	public Admin getAdmin(@PathVariable int adminId) {
		Admin theAdmin=adminService.findById(adminId);
		if(theAdmin==null) {
			throw new AdminNotFoundException("Admin id not found :"+adminId);
		}
		return theAdmin;
	}
	
	@PostMapping("/admins")
	public Admin addAdmin(@RequestBody Admin theAdmin) {
		theAdmin.setAdminId(0);
		adminService.save(theAdmin);
		return theAdmin;
	}
	
	@PutMapping("/admins")
	public Admin updateAdmin(@RequestBody Admin theAdmin) {
		adminService.save(theAdmin);
		return theAdmin;
	}
	
	@DeleteMapping("/admins/{adminId}")
	public String deleteAdmin(@PathVariable int adminId) {
	
		String deletedAdmin=adminService.deleteById(adminId);
		return deletedAdmin;
	}
}

