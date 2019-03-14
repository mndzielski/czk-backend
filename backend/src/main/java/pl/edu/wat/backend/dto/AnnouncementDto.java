package pl.edu.wat.backend.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AnnouncementDto {

    private String type;

    private String category;

    private String province;

    private Boolean wholeProvince;

    private List<String> districts = new ArrayList<>();

    private String title;

    private String description;

    private String timeInterval;
}
