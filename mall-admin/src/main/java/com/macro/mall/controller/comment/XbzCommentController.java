package com.macro.mall.controller.comment;

import com.macro.mall.dto.CommonResult;
import com.macro.mall.dto.comment.XbzCommentQueryParam;
import com.macro.mall.model.XbzComment;
import com.macro.mall.service.commentService.XbzCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
@Controller
@RequestMapping("/comment")
public class XbzCommentController {
    @Autowired
    private XbzCommentService commentService;


    /**
     * 查询所有评价
     */
    //@ApiOperation("查询评价")
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Object list(XbzCommentQueryParam queryParam,
                       @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                       @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
        List<XbzComment> commentList = commentService.getList(queryParam,pageSize,pageNum);
        return new CommonResult().pageSuccess(commentList);
    }

    /**
     * 根据商品id获得
     */
    //@ApiOperation("根据商品id获取商品评价")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object getItem(@PathVariable Long id) {
        XbzComment XbzComment=commentService.getComment(id);
        return new CommonResult().success(XbzComment);
    }
}
