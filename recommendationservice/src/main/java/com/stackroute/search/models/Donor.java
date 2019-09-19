package com.stackroute.search.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.neo4j.ogm.annotation.Id;
import org.neo4j.ogm.annotation.NodeEntity;
import org.neo4j.ogm.annotation.Relationship;

import javax.persistence.Transient;
import java.util.Date;
import java.util.List;

import static org.neo4j.ogm.annotation.Relationship.OUTGOING;

@Data
@NoArgsConstructor
@AllArgsConstructor
@NodeEntity
@JsonIgnoreProperties(ignoreUnknown = true)
//  swagger documentation
@ApiModel(description = "Donor Profile Model")
public class Donor {


    @Id
    private int mid;
    private String id;
    private String name;
    private String email;
    @Transient
    private String password;
    @Transient private String role;
    private long phoneNumber;
    @Transient private int operation;



}
