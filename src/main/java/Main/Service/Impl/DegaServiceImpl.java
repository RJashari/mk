/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Service.Impl;

import Main.BL.Dega;
import Main.Dao.DegaInterface;
import Main.Dao.KampanjaException;
import Main.Service.DegaService;
import java.util.List;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service("degaService")
public class DegaServiceImpl implements DegaService {
     
    @Autowired
    DegaInterface degaDao;
    
    @Override
    public void create(Dega dega) throws KampanjaException {
        
            degaDao.create(dega);
    }

    @Override
    public void edit(Dega dega) throws KampanjaException {
        degaDao.edit(dega);
    }

    @Override
    public void remove(Dega dega) throws KampanjaException {
        degaDao.remove(dega);
    }

    @Override
    public List<Dega> findAll() {
       return degaDao.findAll();
    }

    @Override
    public Dega findById(long id) {
     return degaDao.findById(id);
    }

    @Override
    public Set<Dega> getAllDegetForUser(String username) {
       return degaDao.getAllDegetForUser(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
