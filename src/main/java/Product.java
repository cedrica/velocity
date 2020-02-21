import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Product {

    private UUID id;
    private String name;
    private String type;
    private LocalDateTime date;
    private List<Element> elements;

    public Product(UUID id, String name, String type, LocalDateTime date, List<Element> elements) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.date = date;
        this.elements = elements;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Element> getElements() {
        return elements;
    }

    public void setElements(List<Element> elements) {
        this.elements = elements;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }
}
