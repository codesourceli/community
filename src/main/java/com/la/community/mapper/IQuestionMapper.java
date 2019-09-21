package com.la.community.mapper;

import com.la.community.dto.PaginationDTO;
import com.la.community.entity.Question;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface IQuestionMapper extends Mapper<Question> {
    List<Question> selectPaging(PaginationDTO paginationDTOParam);
}
