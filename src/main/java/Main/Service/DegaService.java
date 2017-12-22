/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Service;

import Main.BL.Dega;
import Main.Dao.KampanjaException;
import java.util.List;
import java.util.Set;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 *
 * @author rinor.jashari
 */
public interface DegaService extends UserDetailsService{
    void create(Dega dega) throws KampanjaException;
    void edit (Dega dega) throws KampanjaException;
    void remove(Dega dega) throws KampanjaException;
    List<Dega> findAll() ;
    Dega findById(long id);
    Set<Dega> getAllDegetForUser(String username);
}
