package cn.yi18.lucene;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.Collector;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.search.highlight.Highlighter;
import org.apache.lucene.search.highlight.InvalidTokenOffsetsException;
import org.apache.lucene.search.highlight.QueryScorer;
import org.apache.lucene.search.highlight.SimpleHTMLFormatter;
import org.apache.lucene.search.highlight.TokenSources;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;
import org.bouncycastle.math.ec.ECAlgorithms;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.yi18.cache.EhCacheEngine;

public class LuceneManage {

	
	

	private static Logger log = LoggerFactory.getLogger(LuceneManage.class);
	private static final int SIZE = 10000;//设置最大的返回结果
	 String path = "/lucene/indextest";//默认存放的目录
	Analyzer analyzer = null;
	 Directory directory  = null;
	 IndexWriterConfig config  = null;
	IndexWriter iwriter =null;
	DirectoryReader ireader =null;
	IndexSearcher isearcher = null;
	
	
	public LuceneManage() {
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		
		try {
			directory = FSDirectory.open( new File(path));
		} catch (IOException e) {
			 log.error("打开Lucene存储{}失败，请确认文件目录是否正确！", path);
			e.printStackTrace();
			
		}
		 config = new IndexWriterConfig(Version.LUCENE_42, analyzer);
	}
	
	public LuceneManage(String path) {
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		
		try {
			directory = FSDirectory.open( new File(path));
			
		} catch (IOException e) {
			log.error("打开Lucene存储{}失败，请确认文件目录是否正确！", path);
			e.printStackTrace();
		}
		 config = new IndexWriterConfig(Version.LUCENE_42, analyzer);
	}
	



	public PageUtil query(String keyword,int page ,int size ) {
		
		List<PageInfo> list = new ArrayList<PageInfo>();
		int total=0;//总的搜索条数
		int time =0;//搜索时间
		try {
			ireader = DirectoryReader.open(directory);
			
			 IndexSearcher isearcher = new IndexSearcher(ireader);
			    // Parse a simple query that searches for "text":
			   // QueryParser parser = new QueryParser(Version.LUCENE_42, "fieldname", analyzer);
			   // Query query = parser.parse("text");
			    
			 String[] fields = {"title","content"};//
			 MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_42, fields , analyzer);
			 Query query=parser.parse(keyword);  
			 Date start = new Date();
		        //TermQuery query=new TermQuery(term);  
		        
		        TopDocs topdocs=isearcher.search(query,null, SIZE); //总的搜索条数
		       
		        ScoreDoc[] scoreDocs=topdocs.scoreDocs; 
		        Date end = new Date();
		        total= scoreDocs.length;//搜索总数
		        time = (int) (end.getTime()-start.getTime());//计算搜索时间
		       
			    //ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
			   // assertEquals(1, hits.length);
			    // Iterate through the results:
		        int startn = (page-1)*size;
		        int endn = page*size;
		        
		        /**
		         * 高亮 "<font color=\"red\">", "</font>" 字体变红，，默认的是<B> </B>加粗
		         */
		        SimpleHTMLFormatter htmlFormatter = new SimpleHTMLFormatter("<font color=\"red\">", "</font>");
		        
		        Highlighter highlighter = new Highlighter(htmlFormatter, new QueryScorer(query));
		        
			    for (int i = startn; i < scoreDocs.length&&i<endn; i++) {
			      Document hitDoc = isearcher.doc(scoreDocs[i].doc);
			      
			      /**
			       * 内容高亮
			       */
			      TokenStream contentStream = TokenSources.getAnyTokenStream(isearcher.getIndexReader(), topdocs.scoreDocs[i].doc, "content", analyzer);
			       String highlightercontent = highlighter.getBestFragment(contentStream, hitDoc.get("content"));
			      
			       /**
			        * 标题高亮
			        */
			      TokenStream titleStream = TokenSources.getAnyTokenStream(isearcher.getIndexReader(), topdocs.scoreDocs[i].doc, "title", analyzer);
			      String highlightertitle = highlighter.getBestFragment(titleStream, hitDoc.get("title"));
			      
			      PageInfo info = new PageInfo();
			      info.setId(Long.parseLong(hitDoc.get("id"))) ;
			      info.setTitle(highlightertitle == null ? hitDoc.get("title"):highlightertitle);
			      info.setContent(highlightercontent == null ? hitDoc.get("content"):highlightercontent);
			      info.setUrl(hitDoc.get("url"));
			      list.add(info);
			    }
			   
				ireader.close();
				directory.close();
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (ParseException e) {
			
			e.printStackTrace();
		} catch (InvalidTokenOffsetsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   
		
	  
		return new PageUtil(page, size, total, time, list);
	}

	
	public void create(PageInfo pageInfo) {
		try {
			iwriter = new IndexWriter(directory, config);
			 Document doc = new Document();
			 //添加
			 Field id = new Field("id", pageInfo.getId()+"",TextField.TYPE_STORED);
			 Field title = new Field("title", pageInfo.getTitle(),TextField.TYPE_STORED);
			 Field content = new Field("content", pageInfo.getContent(),TextField.TYPE_STORED);
			 Field url = new Field("url", pageInfo.getUrl(),TextField.TYPE_STORED);
			 doc.add(id);
			 doc.add(title);
			 doc.add(content);
			 doc.add(url);
			 iwriter.addDocument(doc);
			 iwriter.close();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   

	}

	
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	
	public void update(PageInfo pageInfo) {
		// TODO Auto-generated method stub

	}
	
	

	/**
	 * 取得相关信息
	 * @param fullyQualifiedName
	 * @param keyword
	 * @param page
	 * @param size
	 * @return
	 */
	public List<PageInfo> querycache(String fullyQualifiedName,String keyword, int page, int size) {
		
		@SuppressWarnings("unchecked")
		List<PageInfo> list= (List<PageInfo>) EhCacheEngine.get(fullyQualifiedName , keyword);
		if(list==null)
		{
			list = new ArrayList<PageInfo>();
			
			try {
				ireader = DirectoryReader.open(directory);
				
				 IndexSearcher isearcher = new IndexSearcher(ireader);
				    // Parse a simple query that searches for "text":
				   // QueryParser parser = new QueryParser(Version.LUCENE_42, "fieldname", analyzer);
				   // Query query = parser.parse("text");
				    
				 String[] fields = {"title","content"};//
				 MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_42, fields , analyzer);
				 Query query=parser.parse(keyword);  
			 
			        TopDocs topdocs=isearcher.search(query,null, SIZE); //总的搜索条数
			       
			        ScoreDoc[] scoreDocs=topdocs.scoreDocs; 
			     
			      
			       
				    //ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
				   // assertEquals(1, hits.length);
				    // Iterate through the results:
			        int startn = (page-1)*size;
			        int endn = page*size;
			      
				    for (int i = startn; i < scoreDocs.length&&i<endn; i++) {
				      Document hitDoc = isearcher.doc(scoreDocs[i].doc);

				      PageInfo info = new PageInfo();
				      info.setId(Long.parseLong(hitDoc.get("id"))) ;
				      info.setTitle( hitDoc.get("title"));
				      info.setUrl(hitDoc.get("url"));
				      list.add(info);
				    }
				   
					ireader.close();
					directory.close();
			} catch (IOException e) {
				
				e.printStackTrace();
			} catch (ParseException e) {
				
				e.printStackTrace();
			} 
			EhCacheEngine.add(fullyQualifiedName, keyword, list);
			
		}
		return list;
	}
	


}
