package com.tecsup.petclinic.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OwnerTO {
    private Integer id;
    private String first_name;
    private String last_name;
    private String address;
    private String city;
    private String telephone;
}
