package com.cn.dao;

import com.cn.model.Feedback;
import com.cn.vo.FeedbackEx;

import java.util.List;

/**
 * Created by home on 2017/7/2.
 */



public interface IFeedbackDao {


    public List<Feedback> pageList(FeedbackEx feedbackEx);
    public void insert(Feedback cfeedback);
    public void update(Feedback cfeedback);


}