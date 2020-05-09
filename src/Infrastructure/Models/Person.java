package Infrastructure;

import java.text.MessageFormat;

public class Person {
    protected int id;
    protected String firstName;
    protected String lastName;

    /**
     * Retrieve Id of the person
     *
     * @return Id of the person
     */
    public int getId() {
        return this.id;
    }

    /**
     * This method returns full name of the person
     *
     * @return Full Name
     */
    public String getFullName() {
        return MessageFormat.format("{0} {1}", firstName, lastName);
    }


}
