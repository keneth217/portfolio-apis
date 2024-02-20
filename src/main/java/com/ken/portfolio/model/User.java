package com.ken.portfolio.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

@Data
@Entity
public class User extends BaseEntity{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO,generator="native")
    @GenericGenerator(name = "native",strategy = "native")
    private Long userId;

    @NotBlank(message="Name must not be blank")
    @Size(min=3, message="Name must be at least 3 characters long")
    private String name;


    @NotBlank(message="Profession must not be blank")
    @Size(min=3, message="Profession must be at least 3 characters long")
    private String profession;

    @NotBlank(message="Organisation must not be blank")
    @Size(min=3, message="Organisation must be at least 3 characters long")
    private String organisation;

    @NotBlank(message="Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{10})",message = "Mobile number must be 10 digits")
    private String phone;

    @NotBlank(message="Email must not be blank")
    @Email(message = "Please provide a valid email address" )
    private String email;



//    @NotBlank(message="Password must not be blank")
//    @Size(min=5, message="Password must be at least 5 characters long")
//    @PasswordValidator
//    @JsonIgnore
//    private String pwd;
//
//    @NotBlank(message="Confirm Password must not be blank")
//    @Size(min=5, message="Confirm Password must be at least 5 characters long")
//    @Transient
//    @JsonIgnore
//    private String confirmPwd;
}
