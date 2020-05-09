package Infrastructure;


import java.util.ArrayList;

class MemoryPersonRepository implements Infrastructure.IPersonRepository {
    private final ArrayList<Infrastructure.Person> people;
    private int currentIndex = 0;

    public MemoryPersonRepository() {
        people = new ArrayList<>();
    }

    public ArrayList<Infrastructure.Person> List() {

        return people;
    }

    @Override
    public void Add(String firstName, String lastName) {
        Infrastructure.Person person = new Infrastructure.Person();
        person.firstName = firstName;
        person.lastName = lastName;
        Add(person);
    }

    @Override
    public void Add(Infrastructure.Person value) {
        value.id = ++currentIndex;
        people.add(value);
    }

    @Override
    public void Update(int id, String firstName, String lastName) {
        int index = FindIndexById(id);
        Infrastructure.Person person = FindById(id);
        person.firstName = firstName;
        person.lastName = lastName;
        people.set(index, person);
    }

    @Override
    public Infrastructure.Person FindById(int id) {
        return people.get(FindIndexById(id));
    }

    @Override
    public void Remove(int id) {
        people.remove(FindIndexById(id));
    }

    private int FindIndexById(int id) {
        for (int i = people.size() - 1; i >= 0; i--) {
            if (people.get(i).id == id)
                return i;
        }
        return -1;
    }
}
