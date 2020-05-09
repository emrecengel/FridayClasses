package Infrastructure;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

class JsonPersonRepository implements Infrastructure.IPersonRepository {

    private final Gson _gson;
    private final String _fileName = "People.json";

    public JsonPersonRepository() {
        _gson = new Gson();
    }

    @Override
    public ArrayList<Infrastructure.Person> List() {
        return Read();
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

        ArrayList<Infrastructure.Person> models = Read();
        int currentId = 0;
        if (models.size() > 0) {
            currentId = models.get(0).id;
        }
        value.id = ++currentId;
        models.add(0, value);
        Save(models);

    }

    @Override
    public void Update(int id, String firstName, String lastName) {
        int index = FindIndexById(id);
        Infrastructure.Person person = FindById(id);
        person.firstName = firstName;
        person.lastName = lastName;
        ArrayList<Infrastructure.Person> models = Read();
        models.set(index, person);
        Save(models);
    }

    @Override
    public Infrastructure.Person FindById(int id) {
        return Read().get(FindIndexById(id));
    }

    @Override
    public void Remove(int id) {
        ArrayList<Infrastructure.Person> models = Read();
        models.remove(FindIndexById(id));
        Save(models);

    }

    private int FindIndexById(int id) {
        ArrayList<Infrastructure.Person> models = Read();
        for (int i = models.size() - 1; i >= 0; i--) {
            if (models.get(i).id == id)
                return i;
        }
        return -1;
    }

    //region File Helpers
    private ArrayList<Infrastructure.Person> Read() {
        try {
            File file = new File(_fileName);
            file.createNewFile();
            Scanner myReader = new Scanner(file);
            String data = "";
            while (myReader.hasNextLine()) {
                data += myReader.nextLine();
            }
            myReader.close();

            if (data == null || data.isBlank()) {
                return new ArrayList<>();
            }
            return _gson.fromJson(data, new TypeToken<ArrayList<Infrastructure.Person>>() {
            }.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private void Save(ArrayList<Infrastructure.Person> value) {

        try {

            FileWriter myWriter = new FileWriter(_fileName);
            myWriter.write(_gson.toJson(value));
            myWriter.close();
        } catch (Exception e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }


    }

    //endregion
}
