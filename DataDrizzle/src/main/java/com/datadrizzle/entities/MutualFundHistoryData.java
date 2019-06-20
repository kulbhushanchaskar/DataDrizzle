package com.datadrizzle.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class MutualFundHistoryData {
	private String date;
	private String open; //191.55
	private String close;//": "192.74",
    private String high; //": "193.59",
    private String low; //": "190.30",
    private String volume; //": "18761474"
}