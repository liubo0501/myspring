package com.imooc.model;

import java.util.Date;

public class InteractiveAction {
    private Short actionid;

    private Short interestpointid;

    private Boolean actiontype;

    private Short mediaid;

    private Integer serialnumber;

    private Date createt;
    
    private MediaFile media;

    public MediaFile getMedia() {
		return media;
	}

	public void setMedia(MediaFile media) {
		this.media = media;
	}

	public Short getActionid() {
        return actionid;
    }

    public void setActionid(Short actionid) {
        this.actionid = actionid;
    }

    public Short getInterestpointid() {
        return interestpointid;
    }

    public void setInterestpointid(Short interestpointid) {
        this.interestpointid = interestpointid;
    }

    public Boolean getActiontype() {
        return actiontype;
    }

    public void setActiontype(Boolean actiontype) {
        this.actiontype = actiontype;
    }

    public Short getMediaid() {
        return mediaid;
    }

    public void setMediaid(Short mediaid) {
        this.mediaid = mediaid;
    }

    public Integer getSerialnumber() {
        return serialnumber;
    }

    public void setSerialnumber(Integer serialnumber) {
        this.serialnumber = serialnumber;
    }

    public Date getCreatet() {
        return createt;
    }

    public void setCreatet(Date createt) {
        this.createt = createt;
    }
}