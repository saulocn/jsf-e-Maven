package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.stereotype.Controller;

import br.com.caelum.livraria.dao.UsuarioDao;
import br.com.caelum.livraria.modelo.Usuario;

@Controller
public class LoginBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2039182637211948417L;
	private Usuario usuario = new Usuario();

	@Inject
	private UsuarioDao usuarioDao;
	

	public Usuario getUsuario() {
		return usuario;
	}

	public String efetuaLogin() {
		System.out.println("fazendo login do usuario " + this.usuario.getLogin());
		boolean existe = this.usuarioDao.existe(this.usuario);
		if (existe) {
			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioLogado", this.usuario);
			return "livro?faces-redirect=true";
		}

		FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usu�rio n�o encontrado"));

		return "login?faces-redirect=true";
	}

	public String deslogar() {
		FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}
}
