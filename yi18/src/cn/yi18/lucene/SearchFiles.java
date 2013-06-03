package cn.yi18.lucene;

import java.util.List;

public interface SearchFiles 
{
	public PageUtil query(String keyword,int page ,int size ) ;
	public List<PageInfo> querycache(String fullyQualifiedName,String keyword,int page ,int size );
} 
