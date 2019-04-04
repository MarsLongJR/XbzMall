package com.macro.mall.service.commentService;

import com.macro.mall.dto.comment.XbzCommentQueryParam;
import com.macro.mall.model.XbzComment;

import java.util.List;

/**
 * @ClassName
 * @Description
 * @company:www.xinbeize.com
 * @author:Mars
 */
public interface XbzCommentService {

    List<XbzComment> getList(XbzCommentQueryParam queryParam, Integer pageSize, Integer pageNum);

    XbzComment getComment(Long id);
}
