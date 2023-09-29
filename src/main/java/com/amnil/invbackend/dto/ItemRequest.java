package com.amnil.invbackend.dto;

import lombok.Data;

@Data
public class ItemRequest {
    private Long productId;
    private Long quantity;
}
