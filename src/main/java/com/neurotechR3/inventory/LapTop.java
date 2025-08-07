package com.neurotechR3.inventory;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "laptop_inventory")

// All the info for the Laptop
public class LapTop {

    @Id
    @Column(name = "pc_number")
    private String pcNumber;

    @Column(name = "procured")
    private String procured;

    @Column(name = "device_info")
    private String deviceInfo;

    @Column(name = "location")
    private String location;

    @Column(name = "serial_num")
    private String serialNum;

    @Column(name = "price")
    private double price;

    @Column(name = "notes")
    private String notes;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "user_id")
    private String userID;

    @Column(name = "r3tha_version")
    private String r3thaVersion;

    @Column(name = "anydesk")
    private int anyDesk;

    @Column(name = "last_update")
    private LocalDate lastUpdate;

    @Column(name = "status")
    private Boolean status; // 0 = in use, 1 = available

    public LapTop() {
        // Required for JSPA for declaration
    }

    public void setHistoryLog(List<String> historyLog) {
        this.historyLog = historyLog;
    }

    public List<String> getHistoryLog() {
        return historyLog;
    }

    // Creating the laptop object with all the info
    public LapTop(String PcNumber, String procured, String deviceInfo, String Location, String SerialNum,
            double Price, String Notes, String Email, String Password, String userID, int anyDesk, String r3thaVersion,
            LocalDate lastUpdate,
            Boolean status) {
        this.pcNumber = PcNumber;
        this.procured = procured;
        this.deviceInfo = deviceInfo;
        this.location = Location;
        this.serialNum = SerialNum;
        this.price = Price;
        this.notes = Notes;
        this.email = Email;
        this.password = Password;
        this.userID = userID;
        this.anyDesk = anyDesk;
        this.r3thaVersion = r3thaVersion;
        this.lastUpdate = lastUpdate;
        this.status = status;
    }

    public String getProcured() {
        return procured;
    }

    public void setProcured(String procured) {
        this.procured = procured;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPcNumber() {
        return pcNumber;
    }

    public void setPcNumber(String pcNumber) {
        this.pcNumber = pcNumber;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getDeviceInfo() {
        return deviceInfo;
    }

    public void setDeviceInfo(String deviceInfo) {
        this.deviceInfo = deviceInfo;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getUserID() {
        return userID;
    }

    // Update the userID and add them to the history
    @ElementCollection
    @CollectionTable(name = "laptop_history_log", joinColumns = @JoinColumn(name = "pc_number"))
    @Column(name = "user_id")
    private List<String> historyLog = new ArrayList<>();

    public void updateHistory(String userID) {
        if (userID != null && !userID.isEmpty()) {

            // Avoid duplicate consecutive entries
            if (historyLog.isEmpty() || !historyLog.get(historyLog.size() - 1).equals(userID)) {
                historyLog.add(userID);
                if (historyLog.size() > 3) {
                    historyLog.remove(0); // keep only last 3
                }
            }
        }
        this.userID = userID;
    }

    public int getAnyDesk() {
        return anyDesk;
    }

    public void setAnyDesk(int anyDesk) {
        this.anyDesk = anyDesk;
    }

    public String getR3thaVersion() {
        return r3thaVersion;
    }

    public void setR3thaVersion(String r3thaVersion) {
        this.r3thaVersion = r3thaVersion;
    }

    public LocalDate getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(LocalDate lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

}