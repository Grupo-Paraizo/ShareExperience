package data;

public class FeedExperience {

    private String id;
    private String name;
    private String userUid;
    private String description;
    private String created_at;
    private String updated_at;
    private Boolean isActive;

    public FeedExperience(String id, String name, String userUid, String description, String created_at,
                          String updated_at, Boolean isActive) {
        this.id = id;
        this.name = name;
        this.userUid = userUid;
        this.description = description;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.isActive = isActive;

    }


    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUserUid() {
        return userUid;
    }

    public String getDescription() {
        return description;
    }

    public String getCreated_at() {
        return created_at;
    }

    public String getUpdated_at() {
        return updated_at;
    }

    public Boolean getIsActive() {
        return isActive;
    }
}