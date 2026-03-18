package com.example.boilerroom_labb1.dto;

import java.util.List;

public record BookWrapperDtoV2(List<BookResponseDtoV2> data, String version) {
}
