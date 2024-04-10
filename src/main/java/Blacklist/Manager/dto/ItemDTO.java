package Blacklist.Manager.dto;


import javax.validation.constraints.NotEmpty;

public class ItemDTO {

    private int id;

    @NotEmpty(message = "name should not be blank")
    private String name;
    @NotEmpty(message = "categoryId should not be blank")
    private Integer categoryId;  // We'll use categoryId instead of Category object for simplicity

    // Constructors

    public ItemDTO() {
    }

    public ItemDTO(int id, String name, int categoryId) {
        this.id = id;
        this.name = name;
        this.categoryId = categoryId;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    // Override toString() method for debugging purposes

    @Override
    public String toString() {
        return "ItemDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", categoryId=" + categoryId +
                '}';
    }
}

