package com.ken.portfolio.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data

@AllArgsConstructor
@NoArgsConstructor
public class Response {
    public String StatusCode;
    public String Message;
}
