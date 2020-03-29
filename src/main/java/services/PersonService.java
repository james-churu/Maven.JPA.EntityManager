package services;

import entities.Person;
import org.springframework.stereotype.*;
import javax.persistence.*;
import java.util.List;

@Service
public class PersonService {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpapersistence");
    private EntityManager em = emf.createEntityManager();
    private EntityTransaction tx = em.getTransaction();


    public Person findById(Long personID){
        return em.find( Person.class , personID);
    }
    public List<Person> findAll(Class<Person> classType){
        TypedQuery<Person> query = em.createQuery("SELECT i from " + classType.getSimpleName() +" i", classType);
        return query.getResultList();
    }
    public void update(Person person){

        tx.begin();
        Person newPerson = em.find( Person.class , person.getPersonID() );
        newPerson.setName( person.getName() );
        newPerson.setAge( person.getAge() );
        newPerson.setPetID( person.getPetID() );
        tx.commit();
    }
    public Person create(Long peopleID , String name , Integer age , Integer petID){

        Person person =  new Person();

        person.setPersonID(peopleID);
        person.setName(name);
        person.setAge(age);
        person.setPetID(petID);

        tx.begin();
        em.persist(person);
        tx.commit();

        return person;
    }
    public void delete( Person person) {

        Person persToRemove  = em.find( Person.class , person.getPersonID());

        if( persToRemove != null ) {
            tx.begin();
            em.remove( persToRemove );
            tx.commit();
        }
    }






}
