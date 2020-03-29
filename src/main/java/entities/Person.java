package entities;


import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
public class Person{

    @Id
    @GeneratedValue
    private long peopleID;
    private String name;
    private Integer age;
    private Integer petID;

    @OneToMany()
    private Set<Pet> pets = new HashSet<Pet>();

    public long getPersonID() {
        return peopleID;
    }

    public void setPersonID(long peopleID) {
        this.peopleID = peopleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Integer getPetID() {
        return petID;
    }

    public void setPetID(Integer petID) {
        this.petID = petID;
    }
}
