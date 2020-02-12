package com.luhuihahaha.community.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageDTO {
    private List<QuestionDTO> questionDTOS;
    private Integer page;
    private List<Integer> pages;
    Integer pageCount;


    public void setPagintion(Integer totalCount, Integer page, Integer size) {



        pages = new ArrayList<>();

        if(totalCount % size == 0){
            this.pageCount = totalCount/size;
        }else{
           this.pageCount = totalCount/size+1;
        }

        this.page = page;

        if ((page==1|page==2|page==3)&&pageCount>=5){
            for (Integer i = 1; i <= 5; i++) {
                this.pages.add(i);
            }
        }else if ((page==1|page==2|page==3)&&pageCount<5){
            for (Integer i = 1; i <= pageCount; i++) {
                this.pages.add(i);
            }
        } else if(page>=pageCount-2){
            for (Integer i = pageCount-4; i <= pageCount ; i++) {
                this.pages.add(i);
            }
        }else{
            for (Integer i = page-2; i <= page+2; i++) {
                this.pages.add(i);
            }
        }
    }
}
