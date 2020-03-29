package services;

import entities.Pet;
import org.springframework.stereotype.*;
import javax.persistence.*;
import java.util.List;

@Service
public class PetService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpapersistence");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();


    public Pet findById(Long personID){
        return em.find( Pet.class , personID);
    }
    public List<Pet> findAll(Class<Pet> classType){
        TypedQuery<Pet> query = em.createQuery("SELECT i from " + classType.getSimpleName() +" i", classType);
        return query.getResultList();
    }
    public void update(Pet pet){

        tx.begin();
        Pet newPet = em.find( Pet.class , pet.getIdNum() );
        newPet.setName( pet.getName() );
        newPet.setOwner( pet.getOwner() );
        newPet.setSpecies( pet.getSpecies() );
        tx.commit();
    }
    public Pet create(Long petIDnum , String name , String owner , String specieType ){

        Pet pet =  new Pet();

        pet.setIdNum( petIDnum );
        pet.setName( name );
        pet.setOwner( owner );
        pet.setSpecies( specieType );

        tx.begin();
        em.persist(pet);
        tx.commit();

        return pet;
    }
    public void delete( Pet pet) {

        Pet petToRemove  = em.find( Pet.class , pet.getIdNum() );

        if( petToRemove != null ) {
            tx.begin();
            em.remove( petToRemove );
            tx.commit();
        }
    }
}
