package Infrastructure;

import java.util.ArrayList;

public interface IPersonRepository {

    /**
     * Retrieve List of People
     *
     * @return ArrayList<Infrastructure.Person>
     */
    ArrayList<Infrastructure.Person> List();


    /**
     * Adds a person by passing firstName and lastName
     * @param firstName
     * @param lastName
     */
    void Add(String firstName, String lastName);

    /**
     * Add a person by passing a Person class
     * @param value Person
     */
    void Add(Infrastructure.Person value);


    /**
     * Update a person by providing id
     * @param id
     * @param firstName
     * @param lastName
     */
    void Update(int id, String firstName, String lastName);

    /**
     * Return a person by id
     * @param id
     * @return
     */
    Infrastructure.Person FindById(int id);

    /**
     * Remove a person by id
     * @param id
     */
    void Remove(int id);


}
