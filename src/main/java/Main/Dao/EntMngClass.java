/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main.Dao;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rinor Jashari
 */
@Service
public class EntMngClass {
    EntityManager em = Persistence.createEntityManagerFactory("MenaxhimiCelesave").createEntityManager();
}
