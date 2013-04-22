package cn.yi18.lucene;

import java.io.File;
import java.io.IOException;

import org.apache.lucene.analysis.Analyzer;
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
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

public class DiseaseLucene implements IndexFiles, SearchFiles {

	private static final String path = "/lucene/disease";
	static Analyzer analyzer = null;
	static Directory directory  = null;
	static IndexWriterConfig config  = null;
	IndexWriter iwriter =null;
	DirectoryReader ireader =null;
	IndexSearcher isearcher = null;
	
	static{
		analyzer = new StandardAnalyzer(Version.LUCENE_42);
		
		try {
			directory = FSDirectory.open( new File(path));
			System.out.println(new File(path).getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
		 config = new IndexWriterConfig(Version.LUCENE_42, analyzer);
	}
	
	@Override
	public PageUtil query(String keyword) {
		try {
			ireader = DirectoryReader.open(directory);
			
			 IndexSearcher isearcher = new IndexSearcher(ireader);
			    // Parse a simple query that searches for "text":
			   // QueryParser parser = new QueryParser(Version.LUCENE_42, "fieldname", analyzer);
			   // Query query = parser.parse("text");
			    
			 String[] fields = {"title","content"};
			MultiFieldQueryParser parser = new MultiFieldQueryParser(Version.LUCENE_42, fields , analyzer);
			Query query=parser.parse(keyword);  
			  
		        //TermQuery query=new TermQuery(term);  
		        
		        TopDocs topdocs=isearcher.search(query, 5);  
		        ScoreDoc[] scoreDocs=topdocs.scoreDocs; 
			    //ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
			   // assertEquals(1, hits.length);
			    // Iterate through the results:
			    for (int i = 0; i < scoreDocs.length; i++) {
			      Document hitDoc = isearcher.doc(scoreDocs[i].doc);
			      System.out.println("This is the text to be indexed."+hitDoc.get("id"));
			    }
			   
				ireader.close();
				directory.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	   
		
	  
		return null;
	}

	@Override
	public void create(PageInfo pageInfo) {
		try {
			iwriter = new IndexWriter(directory, config);
			 Document doc = new Document();
			 String text = "This is the text to be indexed.";
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

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(PageInfo pageInfo) {
		// TODO Auto-generated method stub

	}
	
	public static void main(String[] args) {
		DiseaseLucene lucene = new DiseaseLucene();
		PageInfo info = new PageInfo();
		info.setContent("陈磊");
		info.setTitle("11");
		info.setId(22);
		info.setUrl(" ");
		lucene.create(info);
		lucene.query("陈");
		
	}

}
