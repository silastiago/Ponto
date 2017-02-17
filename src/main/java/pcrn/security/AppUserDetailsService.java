package pcrn.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import pcrn.model.Grupo;
import pcrn.model.Pessoa;
import pcrn.repository.Pessoas;
import pcrn.util.cdi.CDIServiceLocator;

public class AppUserDetailsService implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Pessoas pessoas = CDIServiceLocator.getBean(Pessoas.class);
		Pessoa pessoa = pessoas.porLogin(login);
		
		UsuarioSistema user = null;
		
		if (pessoa != null) {
			user = new UsuarioSistema(pessoa, getGrupos(pessoa));
		}
		
		return user;
	}

	private Collection<? extends GrantedAuthority> getGrupos(Pessoa pessoa) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
		
		for (Grupo grupo : pessoa.getGrupos()) {
			authorities.add(new SimpleGrantedAuthority(grupo.getNome().toUpperCase()));
		}
		
		return authorities;
	}

}
