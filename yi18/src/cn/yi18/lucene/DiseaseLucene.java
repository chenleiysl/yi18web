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
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
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
			    QueryParser parser = new QueryParser(Version.LUCENE_42, "fieldname", analyzer);
			    Query query = parser.parse("text");
			    ScoreDoc[] hits = isearcher.search(query, null, 1000).scoreDocs;
			   // assertEquals(1, hits.length);
			    // Iterate through the results:
			    for (int i = 0; i < hits.length; i++) {
			      Document hitDoc = isearcher.doc(hits[i].doc);
			      System.out.println("This is the text to be indexed."+hitDoc.get("fieldname"));
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
			 doc.add(new Field("fieldname", text, TextField.TYPE_STORED));
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
		lucene.create(null);
		lucene.query(null);
		
	}

}
