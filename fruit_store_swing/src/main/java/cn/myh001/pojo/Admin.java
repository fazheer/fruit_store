package cn.myh001.pojo;

public class Admin {
    private Integer id;
    private String name;
    private String password;

    public Admin(String userName, String passWord) {
        this.name = userName;
        this.password = passWord;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Admin) {
            Admin admin = (Admin) obj;
            return admin.name.equals(this.name);
        } else {
            return false;
        }
    }
}
