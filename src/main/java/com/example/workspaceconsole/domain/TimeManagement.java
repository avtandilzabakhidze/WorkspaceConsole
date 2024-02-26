package com.example.workspaceconsole.domain;

public enum TimeManagement {
    HOUR("Hour", 1),
    DAY("Day", 24),
    MONTH("Month", 720), // 30 days per month like mobile provider
    YEAR("Year", 8760); //  365 days per year

    private final String label;
    private final int hoursInUnit;

    TimeManagement(String label, int hoursInUnit) {
        this.label = label;
        this.hoursInUnit = hoursInUnit;
    }

    public String getLabel() {
        return label;
    }

    public int getHoursInUnit() {
        return hoursInUnit;
    }
}
