package in.com.spring.controller;

import org.springframework.stereotype.Controller;
import in.com.spring.beans.User;
import java.util.Collection;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {

	@RequestMapping(value = "/login/loginPass", method = RequestMethod.GET)
	public String loginPass(Model map, HttpSession session,
			HttpServletRequest req) {
		
		System.out.println("==============================================================="); 
		@SuppressWarnings("unused")
		String userName = SecurityContextHolder.getContext()
				.getAuthentication().getName();

		Collection<? extends GrantedAuthority> roleNames = SecurityContextHolder
				.getContext().getAuthentication().getAuthorities();
		String roleName = "";
		for (GrantedAuthority role : roleNames) {
			roleName = role.getAuthority();
			break;
		}

		String retstr = "";

		if ("ROLE_ADMIN".equalsIgnoreCase(roleName)) {
			retstr = "redirect:../admin/AdminHome";
		} else if ("ROLE_SERVICER".equalsIgnoreCase(roleName)) {
			retstr = "redirect:../servicer/ServicerHome";
		} else if ("ROLE_VISITOR".equalsIgnoreCase(roleName)) {
			retstr = "redirect:../visitor/VisitorHome";
		}

		return retstr;
	}

	@RequestMapping(value = "/login/loginFailed", method = RequestMethod.GET)
	public ModelAndView loginFailed(Model map, HttpSession session) {
		return new ModelAndView("global_login.def", "user", new User());
	}

	@RequestMapping(value = "/login/logout")
	public ModelAndView logout(Model map, HttpServletRequest req) {
		req.getSession().invalidate();
		SecurityContextHolder.clearContext();
		return new ModelAndView("global_login.def", "user", new User());
	}
	
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accesssDenied() {
		ModelAndView model = new ModelAndView();
		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);
			model.addObject("username", userDetail.getUsername());
		}
		model.setViewName("403");
		return model;

	}

}
