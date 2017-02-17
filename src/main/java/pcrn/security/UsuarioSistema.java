package pcrn.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import pcrn.model.Pessoa;

public class UsuarioSistema extends User {

	private static final long serialVersionUID = 1L;
	
	private Pessoa pessoa;
	
	public UsuarioSistema(Pessoa pessoa, Collection<? extends GrantedAuthority> authorities) {
		super(pessoa.getLogin(), pessoa.getSenha(), authorities);
		this.pessoa = pessoa;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
}
