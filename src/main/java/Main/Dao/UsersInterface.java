package Main.Dao;





import Main.BL.Dega;
import Main.BL.Users;
import Main.Dao.KampanjaException;
import java.util.List;
import java.util.Set;

public interface UsersInterface {
    
    void create(Users users) throws KampanjaException;
    void edit (Users users) throws KampanjaException;
    void remove(Users users) throws KampanjaException;
    void removeByUsername(String username) throws KampanjaException;
    List<Users> findAll();
    Users findById(int id);
    Users findUsersByUsername(String username);
    void resetUserPassword(int id) throws KampanjaException;

}
