package com.investmentapp.kafkaconsumerservice.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EndDateNotificationMessage {
    private String emailId;
    private String firstName;
    private String lastName;
    private String bankName;
    private double investmentAmount;
    private String endDate;
    private Long daysRemaining;

    private String assetType;

    private String username;


}
