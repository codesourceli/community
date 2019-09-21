package com.la.community.dto;

import lombok.Data;

import java.util.List;

@Data
public class PaginationDTO {

    private List<QuestionDTO> data;
    private List<Integer> showPages;

    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;

    private Integer currentPage;
    private Integer totalPage;
    private Integer count;
    private Integer start;
    private List<Integer> pages;

}
