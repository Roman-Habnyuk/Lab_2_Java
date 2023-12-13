import java.util.Objects;
import java.io.*;
/**
 * Клас, що представляє зоопарк.
 */


public class Zoo implements Serializable {
    private final String name;
    private final String location;

    private Zoo(Builder builder) {
        this.name = Objects.requireNonNull(builder.name, "Name cannot be null");
        this.location = builder.location;
    }

    /**
     * Повертає рядкове представлення зоопарку.
     */
    @Override
    public String toString() {
        return "Zoo{" +
                "name='" + name + '\'' +
                ", location='" + location + '\'' +
                '}';
    }

    /**
     * Порівнює два зоопарки за їхнім іменем та розташуванням.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Zoo zoo = (Zoo) o;
        return Objects.equals(name, zoo.name) &&
                Objects.equals(location, zoo.location);
    }

    /**
     * Генерує хеш-код для зоопарку.
     */
    @Override
    public int hashCode() {
        return Objects.hash(name, location);
    }

    /**
     * Клас для створення об'єкту зоопарку за допомогою патерну Builder.
     */
    public static class Builder {
        private final String name;
        private String location;

        /**
         * Конструктор для об'єкту Builder з обов'язковим параметром - ім'ям зоопарку.
         *
         * @param name ім'я зоопарку
         */
        public Builder(String name) {
            this.name = Objects.requireNonNull(name, "Name cannot be null");
        }

        /**
         * Встановлює розташування зоопарку.
         *
         * @param location розташування зоопарку
         * @return посилання на сам об'єкт Builder для ланцюгового виклику
         */
        public Builder location(String location) {
            this.location = location;
            return this;
        }

        /**
         * Повертає об'єкт зоопарку на основі накопичених параметрів.
         *
         * @return об'єкт зоопарку
         */
        public Zoo build() {
            return new Zoo(this);
        }
    }
}

/**
 * Клас, що представляє тварину в зоопарку.
 */
public class Animal {
    private final String species;
    private final int age;

    /**
     * Конструктор для класу Animal.
     *
     * @param species вид тварини
     * @param age     вік тварини
     */
    public Animal(String species, int age) {
        this.species = Objects.requireNonNull(species, "Species cannot be null");
        this.age = age;
    }

    /**
     * Повертає рядкове представлення тварини.
     */
    @Override
    public String toString() {
        return "Animal{" +
                "species='" + species + '\'' +
                ", age=" + age +
                '}';
    }

    /**
     * Порівнює дві тварини за їхнім видом та віком.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return age == animal.age &&
                Objects.equals(species, animal.species);
    }

    /**
     * Генерує хеш-код для тварини.
     */
    @Override
    public int hashCode() {
        return Objects.hash(species, age);
    }
}

/**
 * Клас, що представляє вольєр для тварин в зоопарку.
 */
public class Enclosure {
    private final String type;
    private final int capacity;

    /**
     * Конструктор для класу Enclosure.
     *
     * @param type     тип вольєру
     * @param capacity вмісткість вольєру
     */
    public Enclosure(String type, int capacity) {
        this.type = Objects.requireNonNull(type, "Type cannot be null");
        this.capacity = capacity;
    }

    /**
     * Повертає рядкове представлення вольєру.
     */
    @Override
    public String toString() {
        return "Enclosure{" +
                "type='" + type + '\'' +
                ", capacity=" + capacity +
                '}';
    }

    /**
     * Порівнює два вольєри за їхнім типом та вмісткістю.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Enclosure enclosure = (Enclosure) o;
        return capacity == enclosure.capacity &&
                Objects.equals(type, enclosure.type);
    }

    /**
     * Генерує хеш-код для вольєру.
     */
    @Override
    public int hashCode() {
        return Objects.hash(type, capacity);
    }
}

/**
     * Записує об'єкт Zoo у файл у заданому форматі.
     *
     * @param zoo      об'єкт Zoo для запису
     * @param filename ім'я файлу
     * @param format   формат запису (json, xml, txt)
     * @throws IOException у випадку помилки запису в файл
     */
    public static void saveToFile(Zoo zoo, String filename, Format format) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(filename))) {
            switch (format) {
                case JSON:
                    saveToJson(zoo, filename);
                    break;
                case XML:
                    saveToXml(zoo, filename);
                    break;
                case TXT:
                    saveToTxt(zoo, filename);
                    break;
                default:
                    throw new IllegalArgumentException("Unsupported format: " + format);
            }
        }
    }

    /**
     * Зчитує об'єкт Zoo з файлу у заданому форматі.
     *
     * @param filename ім'я файлу
     * @param format   формат зчитування (json, xml, txt)
     * @return об'єкт Zoo, зчитаний з файлу
     * @throws IOException            у випадку помилки зчитування з файлу
     * @throws ClassNotFoundException у випадку відсутності класу під час десеріалізації
     */
    public static Zoo loadFromFile(String filename, Format format) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
            switch (format) {
                case JSON:
                    return loadFromJson(filename);
                case XML:
                    return loadFromXml(filename);
                case TXT:
                    return loadFromTxt(filename);
                default:
                    throw new IllegalArgumentException("Unsupported format: " + format);
            }
        }
    }

    private static void saveToJson(Zoo zoo, String filename) throws IOException {
        // Використання Jackson для серіалізації в JSON
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(filename), zoo);
    }

    private static Zoo loadFromJson(String filename) throws IOException {
        // Використання Jackson для десеріалізації з JSON
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(filename), Zoo.class);
    }

    private static void saveToXml(Zoo zoo, String filename) throws IOException {
        // Використання JAXB для серіалізації в XML
        try (Writer writer = new FileWriter(filename)) {
            JAXBContext context = JAXBContext.newInstance(Zoo.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.marshal(zoo, writer);
        }
    }

    private static Zoo loadFromXml(String filename) throws IOException, ClassNotFoundException {
        // Використання JAXB для десеріалізації з XML
        try (Reader reader = new FileReader(filename)) {
            JAXBContext context = JAXBContext.newInstance(Zoo.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return (Zoo) unmarshaller.unmarshal(reader);
        }
    }

    private static void saveToTxt(Zoo zoo, String filename) throws IOException {
        // Запис у текстовий файл (за бажанням)
        try (Writer writer = new FileWriter(filename)) {
            writer.write(zoo.toString());
        }
    }

    private static Zoo loadFromTxt(String filename) throws IOException {
    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line = reader.readLine();
        if (line != null) {
            String[] parts = line.split(",");
            if (parts.length == 2) {
                String name = parts[0];
                String location = parts[1];

                // Тут використовувати будівельник для створення об'єкта Zoo
                return new Zoo.Builder(name)
                        .location(location)
                        .build();
            } else {
                throw new IOException("Invalid format in the text file.");
            }
        } else {
            throw new IOException("Empty file.");
        }
    }
}

    /**
     * Перелік підтримуваних форматів.
     */
    public enum Format {
        JSON,
        XML,
        TXT
    }
}
