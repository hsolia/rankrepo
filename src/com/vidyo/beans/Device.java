package com.vidyo.beans;

import java.io.Serializable;

public class Device implements Serializable {

	private Integer deviceId;
	private String deviceName;
	private String gatewayIP="192.168.0.1";
	private String dialFormat;
	private String dialString;
	private String deviceInfo;
	
	public Integer getDeviceId() {
		return deviceId;
	}
	public void setDeviceId(Integer deviceId) {
		this.deviceId = deviceId;
	}
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
	}
	public String getGatewayIP() {
		return gatewayIP;
	}
	public void setGatewayIP(String gatewayIP) {
		this.gatewayIP = gatewayIP;
	}
	public String getDialString() {
		return dialString;
	}
	public void setDialString(String dialString) {
		this.dialString = dialString;
	}
	public String getDialFormat() {
		return dialFormat;
	}
	public void setDialFormat(String dialFormat) {
		this.dialFormat = dialFormat;
	}
	public String getDeviceInfo() {
		return deviceInfo;
	}
	public void setDeviceInfo(String deviceInfo) {
		this.deviceInfo = deviceInfo;
	}
	
	
	
	
}
