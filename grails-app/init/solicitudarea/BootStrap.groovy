package solicitudarea
import ulsazona.edu.mx.*

class BootStrap {

    def init = { servletContext ->
    	def rolAdmin = new Rol(authority: 'ROLE_ADMIN').save()
		def rolUsuario = new Rol(authority: 'ROLE_USUARIO').save()

		def usuAdmin = new Usuario(username: 'admin',password: '4dm1n').save()
		def usuSis = new Usuario(username: 'usuario',password: 'usuario').save()

		UsuarioRol.create usuAdmin,rolAdmin
		UsuarioRol.create usuSis,rolUsuario

		UsuarioRol.withSession{
 	 	  it.flush()
 	 	  it.clear()
		}
    }
    def destroy = {
    }
}
