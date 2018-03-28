package java.com.spring2.encounter.domain;

/**
 * Created by  邱伟
 * 2018/3/26 17:49
 */

/**
 * 用户模型
 */
public class User {

    private  String id;

    /**
     * 用户名称
     */
    private  String name;

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

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}