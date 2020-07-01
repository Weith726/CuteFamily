package com.opt.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.opt.model.*;

public class OptServlet3 {

protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    List<OptVO> list = new ArrayList<OptVO>();
    
    OptService optSvc = new OptService();
	OptVO optVO = (OptVO) optSvc.getAll();


    optVO.getOptDate();
    optVO.getDocNo();
    optVO.getOptSession();
    
    



    list.add(optVO);


    response.setContentType("application/json");
    response.setCharacterEncoding("UTF-8");
    PrintWriter out = response.getWriter();
    out.write(new Gson().toJson(list));
}

}