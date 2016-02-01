import com.recomdata.redcap.Role
import com.recomdata.redcap.User
import com.recomdata.redcap.UserRole;
import com.recomdata.redcap.dm.Servers;
import com.recomdata.redcap.dm.Projects;

class BootStrap {

    def init = { servletContext ->
		//def adminRole = new Role(authority: 'ROLE_ADMIN').save(flush: true) 
		def userRole = new Role(authority: 'ROLE_USER').save(flush: true)
		
		//def adminUser = new User(username: 'admin', enabled: true, password: 'chbpgd') 
		//adminUser.save(flush: true)
		
		def user = new User(username:'user', enabled: true, password: 'user')
		user.save(flush:true)
		
		//adminUser = User.findByUsername('admin')
		//adminRole = Role.findByAuthority('ROLE_ADMIN')
		//UserRole.create adminUser, adminRole, true
		
		user = User.findByUsername('user')
		userRole = Role.findByAuthority('ROLE_USER')
		UserRole.create user, userRole, true
		
		//def server = new Servers(id:'3', baseURL: 'http://192.168.52.132/redcap_service/', name: 'redcap_local')
		//server.save(flush:true)
		
		//def project1 = new Projects(id:'7', name: 'exceed survey', selected: true, projectID: '4', token: '7F77F0976E46070659A4B33E0C84B44F')
		//project1.save(flush:true)
		
		//def project2 = new Projects(id:'8', name: 'research professional', selected: true, projectID: '5', token: '0A25090D6487E2D5BD03E13C17592F99')
		//project2.save(flush:true)

    }
	
    def destroy = {
    }
}
