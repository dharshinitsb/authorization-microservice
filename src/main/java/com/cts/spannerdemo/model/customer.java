package com.cts.spannerdemo.model;
import com.google.cloud.spring.data.spanner.core.mapping.PrimaryKey;
import com.google.cloud.spring.data.spanner.core.mapping.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;



@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "User")
public class customer {
   @PrimaryKey
   @Generated
    private String customerId;
   // private String firstName;
   // private String lastName;
   private String username;

    private String emailId;
    private String password;
    private String address;
    private String phoneNumber;


}
