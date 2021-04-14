package com.example.sales_management2;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class SalesLead {

    private String id;                                  //auto-generált
    private String href;
    private String creationDate;                 //auto-generált: rögzítés ideje
    private String description;
    private String name;
    private String rating;
    private String referredDate;
    private String statusChangedDate;            //kezdetben: a létrehozás ideje
    private String statusChangeReason;                  //kezdetben: null
    private String type;
    private int estimatedRevenue;
    private SalesLeadPriority salesLeadPriorityType;    //prioritás: automatikusan alacsony
    private SalesLeadState salesLeadStateType;          //automatán: inProgress
    private String validFor;                     //auto-generált - 3 napig valid
    private String creator;

    private Category categoryRef = null;
    private Channel channelRef = null;
    private MarketSegment marketSegmentRef = null;
    private MarketingCampaign marketingCampaignRef = null;
    private Product productRef = null;
    private ProductOffering productOfferingRef = null;
    private ProductSpecification productSpecificationRef = null;
    private SalesOpportunity salesOpportunityRef = null;

    private ArrayList<ContactMedium> contactMediumArrayList;
    private ArrayList<Note> noteArrayList;
    private ArrayList<RelatedParty> relatedPartyArrayList;

    private SalesLeadPriority salesLeadPriority;
    private SalesLeadState salesLeadState;

    public SalesLead(){}

    public SalesLead(String creator, String id, String href, String description, String name, String rating, String referredDate, String type, int estimatedRevenue) {
        this.creator = creator;
        this.id = id;
        this.href = href;
        this.description = description;
        this.name = name;
        this.rating = rating;
        this.referredDate = referredDate;
        this.type = type;
        this.estimatedRevenue = estimatedRevenue;

        this.salesLeadPriority = SalesLeadPriority.low;
        this.salesLeadState = SalesLeadState.inProgress;

        contactMediumArrayList = new ArrayList<ContactMedium>();
        noteArrayList = new ArrayList<Note>();
        relatedPartyArrayList = new ArrayList<RelatedParty>();
    }

    // list adders
    public void addNote(Note note){ noteArrayList.add(note); }
    public void addContactMedium(ContactMedium contactMedium){ contactMediumArrayList.add(contactMedium); }
    public void addRelatedParty (RelatedParty relatedParty) {relatedPartyArrayList.add(relatedParty); }

    //getter setter
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }
    public String getHref() { return href; }
    public void setHref(String href) { this.href = href; }
    public String getCreationDate() { return creationDate; }
    public void setCreationDate(String creationDate) { this.creationDate = creationDate; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getRating() { return rating; }
    public void setRating(String rating) { this.rating = rating; }
    public String getReferredDate() { return referredDate; }
    public void setReferredDate(String referredDate) { this.referredDate = referredDate; }
    public String getStatusChangedDate() { return statusChangedDate; }
    public void setStatusChangedDate(String statusChangedDate) { this.statusChangedDate = statusChangedDate; }
    public String getStatusChangeReason() { return statusChangeReason; }
    public void setStatusChangeReason(String statusChangeReason) { this.statusChangeReason = statusChangeReason; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public int getEstimatedRevenue() { return estimatedRevenue; }
    public void setEstimatedRevenue(int estimatedRevenue) { this.estimatedRevenue = estimatedRevenue; }
    public SalesLeadPriority getSalesLeadPriorityType() { return salesLeadPriorityType; }
    public void setSalesLeadPriorityType(SalesLeadPriority salesLeadPriorityType) { this.salesLeadPriorityType = salesLeadPriorityType; }
    public SalesLeadState getSalesLeadStateType() { return salesLeadStateType; }
    public void setSalesLeadStateType(SalesLeadState salesLeadStateType) { this.salesLeadStateType = salesLeadStateType; }
    public String getValidFor() { return validFor; }
    public void setValidFor(String validFor) { this.validFor = validFor; }
    public MarketSegment getMarketSegmentRef() { return marketSegmentRef; }
    public void setMarketSegmentRef(MarketSegment marketSegmentRef) { this.marketSegmentRef = marketSegmentRef; }
    public MarketingCampaign getMarketingCampaignRef() { return marketingCampaignRef; }
    public void setMarketingCampaignRef(MarketingCampaign marketingCampaignRef) { this.marketingCampaignRef = marketingCampaignRef; }
    public Category getCategoryRef() { return categoryRef; }
    public void setCategoryRef(Category categoryRef) { this.categoryRef = categoryRef; }
    public Channel getChannelRef() { return channelRef; }
    public void setChannelRef(Channel channelRef) { this.channelRef = channelRef; }
    public Product getProductRef() { return productRef; }
    public void setProductRef(Product productRef) { this.productRef = productRef; }
    public ProductOffering getProductOfferingRef() { return productOfferingRef; }
    public void setProductOfferingRef(ProductOffering productOfferingRef) { this.productOfferingRef = productOfferingRef; }
    public ProductSpecification getProductSpecificationRef() { return productSpecificationRef; }
    public void setProductSpecificationRef(ProductSpecification productSpecificationRef) { this.productSpecificationRef = productSpecificationRef; }
    public SalesOpportunity getSalesOpportunityRef() { return salesOpportunityRef; }
    public void setSalesOpportunityRef(SalesOpportunity salesOpportunityRef) { this.salesOpportunityRef = salesOpportunityRef; }
    public ArrayList<ContactMedium> getContactMediumArrayList() { return contactMediumArrayList; }
    public void setContactMediumArrayList(ArrayList<ContactMedium> contactMediumArrayList) { this.contactMediumArrayList = contactMediumArrayList; }
    public ArrayList<Note> getNoteArrayList() { return noteArrayList; }
    public void setNoteArrayList(ArrayList<Note> noteArrayList) { this.noteArrayList = noteArrayList; }
    public ArrayList<RelatedParty> getRelatedPartyArrayList() { return relatedPartyArrayList; }
    public void setRelatedPartyArrayList(ArrayList<RelatedParty> relatedPartyArrayList) { this.relatedPartyArrayList = relatedPartyArrayList; }
    public SalesLeadPriority getSalesLeadPriority() { return salesLeadPriority; }
    public void setSalesLeadPriority(SalesLeadPriority salesLeadPriority) { this.salesLeadPriority = salesLeadPriority; }
    public SalesLeadState getSalesLeadState() { return salesLeadState; }
    public String getCreator() { return creator; }
    public void setCreator(String creator) { this.creator = creator; }
}
