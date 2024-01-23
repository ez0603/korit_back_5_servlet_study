package com.study.servlet_study.entity;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Product {
	private String productName;
	private String price;
	private String size;
	private String color;
}
