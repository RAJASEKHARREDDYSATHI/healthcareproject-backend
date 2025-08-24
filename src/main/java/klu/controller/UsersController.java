package klu.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import klu.model.Users;
import klu.model.UsersManager;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173/")
public class UsersController {

	@Autowired
	UsersManager UM;

	@PostMapping("/signup")
	public String signUp(@RequestBody Users U) {
		return UM.addUser(U);
	}

	@GetMapping("/forgotpassword/{email}")
	public String forgotPassword(@PathVariable("email") String emailid) {
		return UM.recoverPassword(emailid);
	}

	@PostMapping("/signin")
	public String signIn(@RequestBody Users U) {
		// The validateCredentials method in UsersManager now returns
		// "200::token::role::fullname::email" or "401::Invalid Credentials"
		return UM.validateCredentials(U.getEmail(), U.getPassword());
	}

	@PostMapping("/getfullname")
	public String getFullname(@RequestBody Map<String, String> data) {
		return UM.getFullname(data.get("csrid"));
	}

	// The /getRole and /getEmail endpoints are not needed as this information
	// is returned by the /getfullname endpoint.

	// Optional: If you chose Option 2 (making a separate API call)
	// @PostMapping("/userdetails")
	// public Users getUserDetails(@RequestBody Map<String, String> data) {
	//      String email = JWT.validateToken(data.get("token"));
	//      if (!"401".equals(email)) {
	//          return UM.getUserByEmail(email); // Assuming you add this method to UsersManager
	//      }
	//      return null; // Or handle unauthorized access appropriately
	// }
}