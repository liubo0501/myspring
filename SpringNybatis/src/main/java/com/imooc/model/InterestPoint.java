package com.imooc.model;

import java.util.Date;
import java.util.List;

public class InterestPoint {
    private Short interestpointid;

    private String name;

    private Boolean type;

    private Short mapid;

    private String coordinate;

    private String iconurl;

    private Date createt;

    private List<InteractiveAction> actions;
    
    public List<InteractiveAction> getActions() {
		return actions;
	}

	public void setActions(List<InteractiveAction> actions) {
		this.actions = actions;
	}

	public Short getInterestpointid() {
        return interestpointid;
    }

    public void setInterestpointid(Short interestpointid) {
        this.interestpointid = interestpointid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public Short getMapid() {
        return mapid;
    }

    public void setMapid(Short mapid) {
        this.mapid = mapid;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(String coordinate) {
        this.coordinate = coordinate == null ? null : coordinate.trim();
    }

    public String getIconurl() {
        return iconurl;
    }

    public void setIconurl(String iconurl) {
        this.iconurl = iconurl == null ? null : iconurl.trim();
    }

    public Date getCreatet() {
        return createt;
    }

    public void setCreatet(Date createt) {
        this.createt = createt;
    }
}