package com.lnly.business.bo;

import com.lnly.common.model.CountryCompensationStandard;
import com.lnly.common.model.UUser;

import java.io.Serializable;
import java.util.Date;

/**
 * Session  + User Bo
 * @author sojson.com
 *
 */
public class CountryCompensationStandardBo extends CountryCompensationStandard implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer year;
	private String city;
	private String county;
	private Double area;
	private Double countryZbje;
	private Double otherZbje;
	private Double je;
	private String comment;
	private String createTimeStr;
	
	public CountryCompensationStandardBo () {
	}
	
	public CountryCompensationStandardBo(CountryCompensationStandard entity) {
		super(entity);
	}


	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	@Override
	public Integer getYear() {
		return year;
	}

	@Override
	public void setYear(Integer year) {
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

	@Override
	public Double getCountryZbje() {
		return countryZbje;
	}

	@Override
	public void setCountryZbje(Double countryZbje) {
		this.countryZbje = countryZbje;
	}

	@Override
	public Double getOtherZbje() {
		return otherZbje;
	}

	@Override
	public void setOtherZbje(Double otherZbje) {
		this.otherZbje = otherZbje;
	}

	@Override
	public String getComment() {
		return comment;
	}

	@Override
	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
	}

	public Double getJe() {
		return je;
	}

	public void setJe(Double je) {
		this.je = je;
	}
}
