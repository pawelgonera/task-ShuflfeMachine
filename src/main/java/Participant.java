
import java.time.LocalDateTime;

public class Participant {
    private Integer id;

    private LocalDateTime date;
    private String name;
    private String email;
    private String suggestion;
    private String recommender;
    private Boolean wasDrawn = false;

    public Participant(Integer id, LocalDateTime date, String name, String email, String suggestion, String recommender, Boolean wasDrawn) {
        this.id = id;
        this.date = date;
        this.name = name;
        this.email = email;
        this.suggestion = suggestion;
        this.recommender = recommender;
        this.wasDrawn = wasDrawn;
    }

    public Participant() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSuggestion() {
        return suggestion;
    }

    public void setSuggestion(String suggestion) {
        this.suggestion = suggestion;
    }

    public String getRecommender() {
        return recommender;
    }

    public void setRecommender(String recommender) {
        this.recommender = recommender;
    }

    public Boolean getWasDrawn() {
        return wasDrawn;
    }

    public void setWasDrawn(Boolean wasDrawn) {
        this.wasDrawn = wasDrawn;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", date=" + date +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", suggestion='" + suggestion + '\'' +
                ", recommender='" + recommender + '\'' +
                ", wasDrawn=" + wasDrawn +
                '}';
    }
}
