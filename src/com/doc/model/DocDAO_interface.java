package com.doc.model;

import java.util.*;

public interface DocDAO_interface {
	public void insert(DocVO docVO);
    public void update(DocVO docVO);
    public void delete(String docno);
    public DocVO findByPrimaryKey(String docno);
    public List<DocVO> getAll();
    public List<DocVO> getAllByDiv(String divno);
}
