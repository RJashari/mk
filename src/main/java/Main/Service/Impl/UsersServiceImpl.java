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
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
