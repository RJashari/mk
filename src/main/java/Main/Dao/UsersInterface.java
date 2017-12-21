package Main.Dao;





import Main.BL.Users;
import Main.Dao.KampanjaException;
import java.util.List;

public interface UsersInterface {
    
    void create(Users users) throws KampanjaException;
    void edit (Users users) throws KampanjaException;
    void remove(Users users) throws KampanjaException;
    List<Users> findAll() ;
    Users findById(long id);
}
