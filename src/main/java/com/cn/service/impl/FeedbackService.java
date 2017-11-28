package com.cn.service.impl;

import com.cn.dao.IFeedbackDao;
import com.cn.model.Feedback;
import com.cn.service.IFeedbackService;
import com.cn.vo.FeedbackEx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */
@Service("feedbackService")
public class FeedbackService implements IFeedbackService {


    @Autowired
    private IFeedbackDao feedbackDao;



    @Override
    public List<Feedback> getFeedbackPageByEntity(FeedbackEx feedbackEx){
        return feedbackDao.pageList(feedbackEx);
    }


    @Override
    public void addFeedback(Feedback feedback) {
        feedbackDao.insert(feedback);
    }

    @Override
    public void modifyFeedback(Feedback feedback) {
        feedbackDao.update(feedback);
    }



    public IFeedbackDao getFeedbackDao() {
        return feedbackDao;
    }

    public void setFeedbackDao(IFeedbackDao feedbackDao) {
        this.feedbackDao = feedbackDao;
    }

}
