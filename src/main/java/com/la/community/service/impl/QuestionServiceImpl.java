package com.la.community.service.impl;

import com.la.community.dto.PaginationDTO;
import com.la.community.dto.QuestionDTO;
import com.la.community.entity.Question;
import com.la.community.entity.User;
import com.la.community.mapper.IQuestionMapper;
import com.la.community.mapper.IUserMapper;
import com.la.community.service.IQuestionService;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private IQuestionMapper questionMapper;

    @Autowired
    private IUserMapper userMapper;

    /**
     * 首次登录本站的用户信息插入书库
     * @param question
     * @return
     */
    @Override
    public int insert(Question question) {
        int result = questionMapper.insert(question);
        return result;
    }

    /**
     * 查询所有问题列表
     * @param paginationDTOParam
     * @return
     */
    @Override
    public PaginationDTO list(PaginationDTO paginationDTOParam) {
        //查询并设置总数据量
        Question question = new Question();
        int count = questionMapper.selectCount(question);
        paginationDTOParam.setCount(count);

        PaginationDTO  paginationDTOList = pagingMethod(paginationDTOParam);
        return paginationDTOList;
    }

    /**
     * 问题列表分页方法
     * @param paginationDTOParam
     * @return
     */
    private PaginationDTO pagingMethod(PaginationDTO paginationDTOParam) {
        //设置当前页和起始位置
        if(ObjectUtils.isEmpty(paginationDTOParam.getCurrentPage())){
            paginationDTOParam.setStart(0);
            paginationDTOParam.setCurrentPage(1);
        }else{
            int start=10*(paginationDTOParam.getCurrentPage()-1);
            paginationDTOParam.setStart(start);
        }

        //总页数
        Integer totalPage=(int)Math.ceil(paginationDTOParam.getCount()/10f);
        paginationDTOParam.setTotalPage(totalPage);
        //设置页面分页条的展示规则
        List<Integer> showPages=new ArrayList<Integer>();
        showPages.add(paginationDTOParam.getCurrentPage());
        for (int i = 1; i <=3; i++) {
            if (paginationDTOParam.getCurrentPage()>i){
                showPages.add(0,paginationDTOParam.getCurrentPage()-i);
            }                                       //654
            if (paginationDTOParam.getCurrentPage()+i<paginationDTOParam.getTotalPage()+i+1){
                if (paginationDTOParam.getCurrentPage()+i<=paginationDTOParam.getTotalPage()){
                    showPages.add(paginationDTOParam.getCurrentPage()+i);
                }
            }
        }
        paginationDTOParam.setShowPages(showPages);
        //是否展示上一页
        if (paginationDTOParam.getCurrentPage()!=1){
            paginationDTOParam.setShowPrevious(true);
        }
        //是否展示下一页
        if (paginationDTOParam.getCurrentPage()!=paginationDTOParam.getTotalPage()){
            paginationDTOParam.setShowNext(true);
        }
        //是否展示第一页
        if(!paginationDTOParam.getShowPages().contains(1)){
            paginationDTOParam.setShowFirstPage(true);
        }
        //是否展示最后一页
        if(!paginationDTOParam.getShowPages().contains(paginationDTOParam.getTotalPage())){
            paginationDTOParam.setShowEndPage(true);
        }

        List<Question> questionList=questionMapper.selectPaging(paginationDTOParam);
        List<QuestionDTO> questionDTOList=new ArrayList<QuestionDTO>();
        for (Question question1 : questionList) {
            User user=new User();
            user.setId(question1.getCreatorId());
            User user1 = userMapper.selectOne(user);

            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question1,questionDTO);
            questionDTO.setUser(user1);

            questionDTOList.add(questionDTO);
        }

        PaginationDTO paginationDTO=new PaginationDTO();
        BeanUtils.copyProperties(paginationDTOParam,paginationDTO);
        paginationDTO.setData(questionDTOList);

        return paginationDTO;
    }

    /**
     *个人主页的我的问题
     * @param user
     * @return
     */
    @Override
    public PaginationDTO getQuestions(User user,PaginationDTO paginationDTOParam) {
        Question question=new Question();
        question.setCreatorId(user.getId());
        int count =  questionMapper.selectCount(question);
        paginationDTOParam.setCount(count);

        PaginationDTO  paginationDTOList = pagingMethod(paginationDTOParam);
        return paginationDTOList;
    }

    /**
     * 查询问题详情
     * @param id
     * @return
     */
    @Override
    public QuestionDTO getQuestionById(Integer id) {
        Question question = new Question();
        question.setId(id);
        question=questionMapper.selectOne(question);

        User user = new User();
        user.setId(question.getCreatorId());
        user=userMapper.selectOne(user);

        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(user);

        return questionDTO;
    }


}
