package com.cn.dao.impl;

import com.cn.dao.IFeedbackDao;
import com.cn.model.Feedback;
import com.cn.vo.FeedbackEx;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by home on 2017/7/7.
 */

@Repository("feedbackDao")
public class FeedbackDaoImpl extends BaseDaoImpl implements IFeedbackDao {

    @Override
    public List<Feedback> pageList(FeedbackEx feedbackEx) {
        return (List<Feedback>) list("com.cn.dao.FeedbackMapper.selectPageByEntity", feedbackEx);

    }


    @Override
    public void insert(Feedback feedback) {
        addObject("com.cn.dao.FeedbackMapper.insert", feedback);
    }

    @Override
    public void update(Feedback feedback) {
        updateObject("com.cn.dao.FeedbackMapper.updateByPrimaryKey", feedback);
    }
}

