package Main.Dao;
import Main.BL.Dega;
import java.util.HashSet;

import java.util.List;
import java.util.Set;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;

@Repository
public class DegaRepository extends EntMngClass implements DegaInterface {
    
    @Override
    public void create(Dega dega) throws KampanjaException {
                
        try{
            em.getTransaction().begin();
            em.persist(dega);
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
    public void edit(Dega dega) throws KampanjaException {
        try{
            em.getTransaction().begin();
            em.merge(dega);
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
    public void remove(Dega dega) throws KampanjaException {
        try{
            em.getTransaction().begin();
            em.remove(dega);
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
    public List <Dega> findAll() {
        return em.createNamedQuery("Dega.findAll").getResultList();
    }
    @Override
    public Dega findById(long degaID){
        Query query = em.createQuery("SELECT p FROM Dega p WHERE p.nrPersonal = :degaID");
        query.setParameter("degaID", degaID);
        
           return (Dega)query.getSingleResult();
   
    }

 
      @Override
	public Set<Dega> getAllDegetForUser(String username) {
		return new HashSet<>(em
				.createQuery("SELECT p from Dega p where p.pergjegjesi=?")
				.setParameter(0, username)
				.getResultList()
				);
	}
    }



