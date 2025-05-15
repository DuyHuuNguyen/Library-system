package com.g15.library_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
public class TitleAndFirstImageBookDTO {
    public static final String NO_IMAGE = "no-image";
    private String firstImage;
    private String title;

    public boolean isNoImage() {
        return firstImage.equals(NO_IMAGE);
    }
}
