package com.netcracker.edu.backend.entity;

import java.sql.Date;

public interface Subscr {
    Date getStart();
    Date getEnd();
    Wallet getWallet();
    Wallet getUserWallet();
    Boolean getActive();
    void setActive(Boolean isActive);
}
