package com.macro.mall.service.impl.commentServiceImpl;

import com.github.pagehelper.PageHelper;
import com.macro.mall.dto.comment.XbzCommentQueryParam;
import com.macro.mall.mapper.XbzCommentMapper;
import com.macro.mall.model.XbzComment;
import com.macro.mall.model.XbzCommentExample;
import com.macro.mall.service.commentService.XbzCommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Service
public class XbzCommentServiceImpl implements XbzCommentService {
    @Resource
    private XbzCommentMapper commentMapper;


    @Override
    public List<XbzComment> getList(XbzCommentQueryParam queryParam, Integer pageSize, Integer pageNum) {
        PageHelper.startPage(pageNum, pageSize);
        XbzCommentExample example=new XbzCommentExample();

        example.setOrderByClause("sort desc");
        return commentMapper.selectByExample(example);
    }

    @Override
    public XbzComment getComment(Long id) {
        return commentMapper.selectByPrimaryKey(id);
    }
}
