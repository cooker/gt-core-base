package com.github.cooker.entity.major;

import com.github.cooker.entity.Author;
import com.github.cooker.entity.Book;
import com.github.cooker.entity.Label;
import com.github.cooker.entity.Library;
import lombok.Data;

import java.util.List;

/**
 * grant
 * 13/5/2020 4:02 下午
 * 描述：
 */
@Data
public class BookMajor {
    private Integer id;
    private String name;
    //作者id
    private Integer aid;
    //书架id
    private Integer lid;

    private Author author;
    private Library library;
    private List<Label> labels;
}
