package com.stackroute.cause.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cause {
    @Id
    private String id;
    private String name;
    private String receiverId;
    private String causeType;
    private String causeDescription;
    private String timestamp;
    private String location;
    private  long amount;
}
