package zobi.paulo.kalawi.model;


import java.util.HashMap;
import java.util.Map;

public class Zamel {

    private String id;
    private String name;
    private String birthDate;
    private String birthPlace;

    public Zamel() {}

    public Zamel(String name, String birthDate, String birthPlace) {
        this.name = name;
        this.birthDate = birthDate;
        this.birthPlace = birthPlace;
    }

    public Map<String, Object> toMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", this.name);
        map.put("birthDate", this.birthDate);
        map.put("birthPlace", this.birthPlace);

        return map;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }
}
