package pers.alyssa.drinkmachine;

public class Drink {
    private final Integer id;

    private final String name;

    private String description;

    public Drink(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getId() {
        return id;
    }
}
