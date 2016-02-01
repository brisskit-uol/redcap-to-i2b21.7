package com.recomdata.redcap.auth

import org.codehaus.groovy.grails.plugins.springsecurity.SpringSecurityUtils
import grails.plugins.springsecurity.Secured;
import org.springframework.security.authentication.AccountExpiredException
import org.springframework.security.authentication.CredentialsExpiredException
import org.springframework.security.authentication.DisabledException
import org.springframework.security.authentication.LockedException
import org.springframework.security.core.context.SecurityContextHolder as SCH
import org.springframework.security.web.WebAttributes
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter
import javax.servlet.http.HttpServletResponse

import com.recomdata.redcap.User;

@Secured(['IS_AUTHENTICATED_FULLY'])
class PasswordController {
	
	def springSecurityService

	def passwordEncoder
	
	static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def index = {
		render view: 'changepassword'
	}
	
	def save = {
		def user = springSecurityService.currentUser;
		if(!user) {
			redirect controller: 'login', action: 'auth', params: params
			return;
		}
		
		String currentPassword = params.currentPassword;
		if(!(passwordEncoder.isPasswordValid(user.password, currentPassword, null))) {
			flash.message = " Current password is incorrect.";
			render view: 'changepassword'
			return;
		}
		
		String newPassword = params.newPassword;
		String confirmNewPassword = params.confirmNewPassword;		
		if(!(newPassword.equals(confirmNewPassword))) {
			flash.message = "New passwords don't match.";
			render view: 'changepassword'
			return;
		}		
		
		if(currentPassword.equals(newPassword)) {
			flash.message = "New password and current password cannot be the same.";
			render view: 'changepassword'
			return;
		}
		
		user.password = newPassword;
		user.passwordExpired = false;
		if(!(user.save(flush:true))) {			
			flash.message = "Password could not be updated.";
			render view: 'changepassword'
			return;
		}
		
		redirect controller: 'servers'
	}
}