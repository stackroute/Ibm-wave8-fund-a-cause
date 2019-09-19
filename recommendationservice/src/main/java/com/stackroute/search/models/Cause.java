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
@ApiModel(description = "Cause Profile Model")
public class Cause {


    @Id
    private String id;
    private String name;
    private String receiverId;
    private String causeType;
    private String causeDescription;
    private String timestamp;
    private String location;



}
