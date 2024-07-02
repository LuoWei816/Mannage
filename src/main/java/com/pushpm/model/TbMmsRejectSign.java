package com.pushpm.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TbMmsRejectSign {
    private int ID;
    private  String CONTENT;
    private int TYPE;
    private int STATUS;
}
