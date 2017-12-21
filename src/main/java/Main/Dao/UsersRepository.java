package Main.Dao;
import Main.BL.Users;

import java.util.List;
import javax.persistence.NoResultException;
import org.apache.log4j.Logger;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class UsersRepository extends EntMngClass implements UsersInterface {
    
    @Override
    public void create(Users users) throws KampanjaException {
                
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
    public Users findById(long usersID){
        Query query = em.createQuery("SELECT p FROM Users p WHERE p.nrPersonal = :usersID");
        query.setParameter("usersID", usersID);
        
           return (Users)query.getSingleResult();
   
    }

}

