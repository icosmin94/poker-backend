package com.project.poker.commonlib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardNumber {
    Ace("A"), One("1"), Two("2"), Three("3"),
    Four("4"), Five("5"), Six("6"), Seven("7"),
    Eight("8"), Nine("9"), Ten("10"), Jack("J"),
    Queen("Q"), King("K");

    private String value;
}
