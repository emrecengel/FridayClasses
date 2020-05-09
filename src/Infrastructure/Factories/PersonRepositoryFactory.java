package Infrastructure;

public class PersonRepositoryFactory {

    private final String _typeOfRepository;

    public PersonRepositoryFactory(String typeOfRepository) {

        _typeOfRepository = typeOfRepository;
    }

    public Infrastructure.IPersonRepository RetrieveRepository() {
        switch (_typeOfRepository) {
            case "Memory":
                return new Infrastructure.MemoryPersonRepository();
            case "Json":
                return new Infrastructure.JsonPersonRepository();
            default:
                new Exception("Invalid Repository").printStackTrace();
        }
        return null;
    }
}
