package com.xiaoheibaby.app.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PixelData {
    private int x;
    private int y;
    private String color;
}
