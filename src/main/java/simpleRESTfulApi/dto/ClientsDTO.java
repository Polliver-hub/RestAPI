package simpleRESTfulApi.dto;

import lombok.Data;

@Data
public class ClientsDTO {
    private Integer id;
    private String name;
    private String email;
    private String phone;
}
