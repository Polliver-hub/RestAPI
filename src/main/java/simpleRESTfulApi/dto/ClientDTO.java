package simpleRESTfulApi.dto;

//import lombok.Data;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClientDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
}
