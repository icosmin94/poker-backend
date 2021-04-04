package com.project.poker.commonlib.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CardColor {
    Club("C"), Diamond("D"), Heart("H"), Spade("S");
    private String value;
}
