package com.stackroute.donationservice.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Component
@Document(collection = "donation")
public class Donation {

    @Id
    private String donationId;
    private String receiverId;
    private String donorId;
    private String causeId;
    private long amount;

    public Donation() {

    }

    public Donation(String donationId, String receiverId, String donorId, String causeId, Long amount) {
        this.donationId = donationId;
        this.receiverId = receiverId;
        this.donorId = donorId;
        this.causeId = causeId;
        this.amount = amount;
    }

    public String getDonationId() {
        return donationId;
    }

    public void setDonationId(String donationId) {
        this.donationId = donationId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getDonorId() {
        return donorId;
    }

    public void setDonorId(String donorId) {
        this.donorId = donorId;
    }

    public String getCauseId() {
        return causeId;
    }

    public void setCauseId(String causeId) {
        this.causeId = causeId;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }
}
