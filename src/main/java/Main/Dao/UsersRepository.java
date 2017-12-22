package Main.Dao;
import Main.BL.Dega;
import Main.BL.Users;


import java.util.List;
import java.util.Set;
import javax.persistence.NoResultException;
import org.apache.log4j.Logger;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository extends EntMngClass implements UsersInterface {
     private final Logger LOGGER = Logger.getLogger(UsersRepository.class);
    private String DEFAULT_PASSWORD ="$2a$10$5kCKO/IAcqcrAy0IzrHFK.kEVBeBKVn8j/m4xcN7TTBhb1RJ3GJ7S";//password00;
    
    @Override
    public void create(Users users) throws KampanjaException {
        LOGGER.info("Duke krijuar shfrytezuesin.");
        try{
            em.getTransaction().begin();
            em.persist(users);
            em.getTransaction().commit();
        }
        catch(Throwable thro){
            
            if(thro.getMessage().contains("2627")){
            
                    throw new KampanjaException("E dhëna egziston !");
            }
        else{
                throw new KampanjaException("Create : "+thro.getClass()+" - "+thro.getMessage());
                }
    }
}
    @Override
    public void edit(Users users) throws KampanjaException {
        try{
            em.getTransaction().begin();
            em.merge(users);
            em.getTransaction().commit();
        }
        catch(Throwable thro){
            if(thro.getMessage().contains("2627")){
                    throw new KampanjaException("E dhëna egziston");
            }
            else{
                throw new KampanjaException("Update: "+thro.getClass()+" - "+thro.getMessage());
            }
                
        }
    }
    @Override
    public void remove(Users users) throws KampanjaException {
        try{
            em.getTransaction().begin();
            em.remove(users);
            em.getTransaction().commit();
        }catch(Throwable thro){
            if(thro.getMessage().contains("547")){
                throw new KampanjaException("E dhëna është përdorur, nuk mund ta fshini!!");
            }
            else{
                 throw new KampanjaException("Remove: "+thro.getClass()+" - "+thro.getMessage());
            }
        }
        
    }
    @Override
    public List <Users> findAll() {
        return em.createNamedQuery("Users.findAll").getResultList();
    }
    @Override
    public Users findById(int usersID){
        Query query = em.createQuery("SELECT p FROM Users p WHERE p.nrPersonal = :usersID");
        query.setParameter("usersID", usersID);
        
           return (Users)query.getSingleResult();
    }

    @Override
    public void removeByUsername(String username) throws KampanjaException {
       LOGGER.info("Removing user: " + username);
        try{
            Users user = this.findUsersByUsername(username);
            em.getTransaction().begin();
            em.remove(user);
            em.getTransaction().commit();
        }catch(Throwable thro){
            if(thro.getMessage().contains("547")){
                throw new KampanjaException("E dhëna është përdorur, nuk mund ta fshini!!");
            }
            else{
                 throw new KampanjaException("Remove: "+thro.getClass()+" - "+thro.getMessage());
            }
        }
    }

    @Override
    public Users findUsersByUsername(String username) {
        LOGGER.info("Duke kerkuar usernin me username :"+username);
        
        Query query = em.createQuery("SELECT u FROM Users u WHERE u.username = :username");
        query.setParameter("username", username);
        
           return (Users)query.getSingleResult();
    }

    @Override
    public void resetUserPassword(int id) throws KampanjaException {
          LOGGER.info("Resetting user password: " + id);
        try{
            Users user = this.findById(id);
            em.getTransaction().begin();
            user.setPassword(DEFAULT_PASSWORD);
            em.merge(user);
            em.getTransaction().commit();
        }
        catch(Throwable thro){
            
            
                throw new KampanjaException("Create : "+thro.getClass()+" - "+thro.getMessage());
                }
    }

}

