/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Service.Impl;

import Main.BL.Users;
import Main.Dao.KampanjaException;
import Main.Dao.UsersInterface;
import Main.Service.UsersService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("usersService")
public class UsersServiceImpl implements UsersService {

    @Autowired
    UsersInterface usersDao;
    
    @Override
    public void create(Users users) throws KampanjaException {
       usersDao.create(users);
    }

    @Override
    public void edit(Users users) throws KampanjaException {
       usersDao.edit(users);
    }

    @Override
    public void remove(Users users) throws KampanjaException {
      usersDao.remove(users);
    }

    @Override
    public void removeByUsername(String username) throws KampanjaException {
        usersDao.removeByUsername(username);
    }

    @Override
    public List<Users> findAll() {
       return usersDao.findAll();
    }

    @Override
    public Users findById(int id) {
        return usersDao.findById(id);
    }

    @Override
    public Users findUsersByUsername(String username) {
        return usersDao.findUsersByUsername(username);
    }

    @Override
    public void resetUserPassword(int id) throws KampanjaException {
        usersDao.resetUserPassword(id);
    }
 private List<GrantedAuthority> buildUserAuthority(Users user) {
		Set<GrantedAuthority> authos = new HashSet<>();
//		for(UserRole userRole: userRoles) {
			authos.add(new SimpleGrantedAuthority(user.getRole()));
//		}
		return new ArrayList<>(authos);
	}
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users kauser = usersDao.findUsersByUsername(username);
       if(null == kauser) {
			throw new UsernameNotFoundException("No user named " + username + " exists");
		}
       return buildUserForAuthentication(kauser);
    }
     private User buildUserForAuthentication(Users user) {
        
		return new User(user.getUsername(), user.getPassword(),true,  true, true, true, this.buildUserAuthority(user));
	}
    

}

