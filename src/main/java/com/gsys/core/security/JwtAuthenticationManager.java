package com.gsys.core.security;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.gsys.core.config.AppContext;
import com.gsys.core.dto.LoginDTO;
import com.gsys.model.Administrator;
import com.gsys.repository.AdministratorRepository;
public class JwtAuthenticationManager implements AuthenticationManager {
	private AdministratorRepository administratorRepository;

	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		Administrator user = this.getAdministratorRepository().findByLoginAndPassword(auth.getName(), (String) auth.getCredentials());
		if(user != null) {
			return new UsernamePasswordAuthenticationToken(toDTO(user), auth.getCredentials());
		}
		
		throw new BadCredentialsException("Usuário e/ou senha inválidos.");
	}

	
	private LoginDTO toDTO(Administrator user) {
		LoginDTO loginDTO = new LoginDTO();
		loginDTO.setLogin(user.getLogin());
		loginDTO.setId(user.getId());
		return loginDTO;
	}
	
	protected AdministratorRepository getAdministratorRepository() {

        if (this.administratorRepository == null) {
            this.administratorRepository = AppContext.getBean(AdministratorRepository.class);
        }

        return this.administratorRepository;
    }

}