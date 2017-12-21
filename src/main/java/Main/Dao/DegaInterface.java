package Main.Dao;





import Main.BL.Dega;
import Main.Dao.KampanjaException;
import java.util.List;

public interface DegaInterface {
    
    void create(Dega dega) throws KampanjaException;
    void edit (Dega dega) throws KampanjaException;
    void remove(Dega dega) throws KampanjaException;
    List<Dega> findAll() ;
    Dega findById(long id);
}
