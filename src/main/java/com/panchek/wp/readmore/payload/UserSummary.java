package com.panchek.wp.readmore.payload;

public class UserSummary {
    private Long id;
    private String username;
    private String name;
    private String roleName;

    public UserSummary(Long id, String username, String name, String roleName) {
        this.id = id;
        this.username = username;
        this.name = name;
        this.roleName= roleName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
