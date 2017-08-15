package com.lnly.business.bo;

import com.lnly.common.model.CountryCompensationStandard;

import java.io.Serializable;

/**
 * Session  + User Bo
 * @author sojson.com
 *
 */
public class LocalCompensationStandardBo extends CountryCompensationStandard implements Serializable {

	private static final long serialVersionUID = 1L;

	private String year;
	private String city;
	private String county;
	private Double area;
	private Double je;
	private String createTimeStr;

	public LocalCompensationStandardBo() {
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

    @Override
    public String getYear() {
        return year;
    }

    @Override
    public void setYear(String year) {
        this.year = year;
    }

    @Override
	public String getCity() {
		return city;
	}

	@Override
	public void setCity(String city) {
		this.city = city;
	}

	@Override
	public String getCounty() {
		return county;
	}

	@Override
	public void setCounty(String county) {
		this.county = county;
	}

	@Override
	public Double getArea() {
		return area;
	}

	@Override
	public void setArea(Double area) {
		this.area = area;
	}

	public Double getJe() {
		return je;
	}

	public void setJe(Double je) {
		this.je = je;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}
}
