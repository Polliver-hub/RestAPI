package simpleRESTfulApi.model;
import lombok.*;

@Getter
@Setter
public class Client {
    private Integer id;
    private String name;
    private String email;
    private String phone;
}
