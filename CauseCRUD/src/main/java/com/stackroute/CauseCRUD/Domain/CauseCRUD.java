package com.stackroute.CauseCRUD.Domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CauseCRUD {
    @Id
    private String id;
    private String name;
    private String receiverId;
    private String causeType;
    private String causeDescription;
    private String timestamp;
    private String location;
}
