package com.lvqiang.springcloud.practice.server.utils;

import com.lvqiang.springcloud.practice.server.vo.ResultVO;

public class ResutlVOUtils {

    public static ResultVO success(Object object) {
        ResultVO resultVO = new ResultVO();
        resultVO.setData(object);
        resultVO.setCode(0);
        resultVO.setMsg("成功");
        return resultVO;
    }
}
