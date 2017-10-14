package com.ankurpathak.security;
import com.ankurpathak.server.domain.model.User;
import com.ankurpathak.server.domain.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service("customUserDetailsService")
public class CustomUserDetailsService implements UserDetailsService {
	private IUserRepository userRepository;
	static final boolean ENABLED = true;
	static final boolean ACCOUNT_NOT_EXPIRED = true;
	static final boolean CREDENTIALS_NOT_EXPIRED = true;
	static final boolean ACCOUNT_NOT_LOCKED = true;


	
	@Autowired
    public CustomUserDetailsService(IUserRepository userRepository) {
        this.userRepository = userRepository;
	}


	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> userOpt = userRepository.findByUsername(username);
		if(!userOpt.isPresent()){
            userOpt = userRepository.findByEmail(username);
            if(!userOpt.isPresent()){
				userOpt = userRepository.findByContact(username);

                if(!userOpt.isPresent()){
                    throw new UsernameNotFoundException("No User found with username: "+username);
                }
            }
		}
        User user = userOpt.get();
        List<String> userRoleNames = getRoles(user.getRoles());
        return new CustomUserDetails(user, ENABLED,ACCOUNT_NOT_LOCKED, CREDENTIALS_NOT_EXPIRED, ACCOUNT_NOT_EXPIRED, userRoleNames);
	}





	public  List<String> getRoles(Set<User.Role> userRoles){
		List<String> roles = userRoles.stream().map(x -> x.name()).collect(Collectors.toList());
		return roles;
	}



		
}
