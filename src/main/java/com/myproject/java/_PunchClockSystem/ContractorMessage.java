package com.myproject.java._PunchClockSystem;

class ContractorMessage extends RoleMessage {
    private String contractEndDate;

    public ContractorMessage(String contractEndDate) {
        this.contractEndDate = contractEndDate;
    }

    @Override
    public void displayRoleMessage() {
        System.out.println("Contractor: Your contract expires on " + contractEndDate + ".");
    }
}
