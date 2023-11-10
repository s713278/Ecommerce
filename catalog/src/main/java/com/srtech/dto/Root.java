package com.srtech.dto;

import java.util.ArrayList;

import lombok.Data;

@Data
public class Root{
    public ArrayList<ProductDTO> products;
    public int total;
    public int skip;
    public int limit;
}

