package com.example.sales_management2;

import java.time.LocalDateTime;
import java.util.Currency;

public class Mini_SalesLead {

    private String id;
    private LocalDateTime creationDate;
    private SalesLeadPriority salesLeadPriorityType;
    private String type;
    private Currency estimatedRevenue;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public LocalDateTime getCreationDate() { return creationDate; }
    public void setCreationDate(LocalDateTime creationDate) { this.creationDate = creationDate; }
    public SalesLeadPriority getSalesLeadPriorityType() { return salesLeadPriorityType; }
    public void setSalesLeadPriorityType(SalesLeadPriority salesLeadPriorityType) { this.salesLeadPriorityType = salesLeadPriorityType; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public Currency getEstimatedRevenue() { return estimatedRevenue; }
    public void setEstimatedRevenue(Currency estimatedRevenue) { this.estimatedRevenue = estimatedRevenue; }
}
