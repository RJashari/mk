package Main.Dao;

import Main.BL.Dega;

import java.util.List;
import java.util.Set;

public interface DegaInterface {
    
    void create(Dega dega) throws KampanjaException;
    void edit (Dega dega) throws KampanjaException;
    void remove(Dega dega) throws KampanjaException;
    List<Dega> findAll() ;
    Dega findById(long id);
    Set<Dega> getAllDegetForUser(String username);
}
