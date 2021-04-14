package com.example.sales_management2;

import java.time.LocalDateTime;

public class MiniSalesItem {

    private String id;
    private String creationDate;
    private SalesLeadPriority salesLeadPriority;
    private String type;
    private int estimatedRevenue;
    private String creator;

    public MiniSalesItem(){
    }

    public MiniSalesItem(String id, String creationDate, SalesLeadPriority salesLeadPriority, String type, int estimatedRevenue, String creator) {
        this.id = id;
        this.creationDate = creationDate;
        this.salesLeadPriority = salesLeadPriority;
        this.type = type;
        this.estimatedRevenue = estimatedRevenue;
        this.creator = creator;
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getCreationDate() { return creationDate; }
    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }
    public SalesLeadPriority getSalesLeadPriority() { return salesLeadPriority; }
    public void setSalesLeadPriorityType(SalesLeadPriority salesLeadPriorityType) { this.salesLeadPriority = salesLeadPriorityType; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getEstimatedRevenue() { return estimatedRevenue; }
    public void setEstimatedRevenue(int estimatedRevenue) { this.estimatedRevenue = estimatedRevenue; }
    public String getCreator() { return creator; }
}
