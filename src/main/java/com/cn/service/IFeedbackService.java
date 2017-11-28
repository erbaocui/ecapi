package com.cn.service;

import com.cn.model.Feedback;
import com.cn.vo.FeedbackEx;

import java.util.List;

public interface IFeedbackService {
    public List<Feedback> getFeedbackPageByEntity(FeedbackEx feedbackEx);
    public void addFeedback(Feedback feedback);
    public void modifyFeedback(Feedback feedback);

}
