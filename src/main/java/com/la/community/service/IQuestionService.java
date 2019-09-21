package com.la.community.service;

import com.la.community.dto.PaginationDTO;
import com.la.community.dto.QuestionDTO;
import com.la.community.entity.Question;
import com.la.community.entity.User;

import java.util.List;

public interface IQuestionService {
    int insert(Question question);

    PaginationDTO list(PaginationDTO paginationDTOParam);

    PaginationDTO getQuestions(User user,PaginationDTO paginationDTOParam);

    QuestionDTO getQuestions(Integer id);
}
